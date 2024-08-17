package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbsoccer;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerDto;
import com.thc.smspr2.dto.TbsoccerfileDto;
import com.thc.smspr2.dto.TbsoccerlikeDto;
import com.thc.smspr2.exception.NoAuthorizationException;
import com.thc.smspr2.mapper.TbsoccerMapper;
import com.thc.smspr2.repository.TbsoccerRepository;
import com.thc.smspr2.service.TbsoccerService;
import com.thc.smspr2.service.TbsoccerfileService;
import com.thc.smspr2.service.TbsoccerlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbsoccerServiceImpl implements TbsoccerService {

    private final TbsoccerRepository tbsoccerRepository;
    private final TbsoccerMapper tbsoccerMapper;
    private final TbsoccerfileService tbsoccerfileService;
    private final TbsoccerlikeService tbsoccerlikeService;
    public TbsoccerServiceImpl(
            TbsoccerRepository tbsoccerRepository
            , TbsoccerMapper tbsoccerMapper
            , TbsoccerfileService tbsoccerfileService
            , TbsoccerlikeService tbsoccerlikeService
    ) {
        this.tbsoccerRepository = tbsoccerRepository;
        this.tbsoccerMapper = tbsoccerMapper;
        this.tbsoccerfileService = tbsoccerfileService;
        this.tbsoccerlikeService = tbsoccerlikeService;
    }

    @Override
    public TbsoccerDto.CreateResDto create(TbsoccerDto.CreateServDto param){
        //사용자 정보 강제 입력
        param.setTbuserId(param.getReqTbuserId());

        TbsoccerDto.CreateResDto createResDto = tbsoccerRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbsoccerfileUrls().size();i++){
            tbsoccerfileService.create(
                    TbsoccerfileDto.CreateReqDto.builder()
                            .tbsoccerId(createResDto.getId())
                            .type(param.getTbsoccerfileTypes().get(i))
                            .url(param.getTbsoccerfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbsoccerDto.CreateResDto update(TbsoccerDto.UpdateServDto param){
        Tbsoccer tbsoccer = tbsoccerRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin() && !tbsoccer.getTbuserId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }

        if(param.getDeleted() != null){
            tbsoccer.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbsoccer.setProcess(param.getProcess());
        }

        /*if(param.getTbuserId() != null){
            tbsoccer.setTbuserId(param.getTbuserId());
        }*/
        if(param.getTitle() != null){
            tbsoccer.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbsoccer.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbsoccer.setCountread(param.getCountread());
        }
        */
        tbsoccerRepository.save(tbsoccer);
        return tbsoccer.toCreateResDto();
    }
    public TbsoccerDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbsoccerDto.UpdateServDto newParam = TbsoccerDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbsoccerDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbsoccerDto.UpdateServDto newParam = TbsoccerDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbsoccerDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbsoccerDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbsoccerDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbsoccerDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbsoccer tbsoccer = tbsoccerRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbsoccer.setCountread(++countread);
        tbsoccerRepository.save(tbsoccer);

        //조회수 업데이트!!
        update(TbsoccerDto.UpdateServDto.builder().id(selectResDto.getId()).countread(++countread).isAdmin(true).build());
        return selectResDto;
    }

    public TbsoccerDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbsoccerDto.DetailResDto selectResDto = tbsoccerMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbsoccerfiles(
                tbsoccerfileService.list(TbsoccerfileDto.ListReqDto.builder().tbsoccerId(selectResDto.getId()).build())
        );
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbsoccerlikeService.exist(TbsoccerlikeDto.CreateReqDto.builder().tbsoccerId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        return selectResDto;
    }

    @Override
    public List<TbsoccerDto.DetailResDto> list(TbsoccerDto.ListServDto param){
        return detailList(tbsoccerMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbsoccerDto.PagedListServDto param){
        int[] returnSize = param.init(tbsoccerMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbsoccerMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbsoccerDto.DetailResDto> scrollList(TbsoccerDto.ScrollListServDto param){
        param.init();
        return detailList(tbsoccerMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbsoccerDto.DetailResDto> detailList(List<TbsoccerDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbsoccerDto.DetailResDto> newList = new ArrayList<>();
        for(TbsoccerDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
