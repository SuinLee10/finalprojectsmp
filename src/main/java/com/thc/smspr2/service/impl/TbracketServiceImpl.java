package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbracket;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketDto;
import com.thc.smspr2.dto.TbracketfileDto;
import com.thc.smspr2.dto.TbracketlikeDto;
import com.thc.smspr2.exception.NoAuthorizationException;
import com.thc.smspr2.mapper.TbracketMapper;
import com.thc.smspr2.repository.TbracketRepository;
import com.thc.smspr2.service.TbracketService;
import com.thc.smspr2.service.TbracketfileService;
import com.thc.smspr2.service.TbracketlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbracketServiceImpl implements TbracketService {

    private final TbracketRepository tbracketRepository;
    private final TbracketMapper tbracketMapper;
    private final TbracketfileService tbracketfileService;
    private final TbracketlikeService tbracketlikeService;
    public TbracketServiceImpl(
            TbracketRepository tbracketRepository
            , TbracketMapper tbracketMapper
            , TbracketfileService tbracketfileService
            , TbracketlikeService tbracketlikeService
    ) {
        this.tbracketRepository = tbracketRepository;
        this.tbracketMapper = tbracketMapper;
        this.tbracketfileService = tbracketfileService;
        this.tbracketlikeService = tbracketlikeService;
    }

    @Override
    public TbracketDto.CreateResDto create(TbracketDto.CreateServDto param){
        //사용자 정보 강제 입력
        param.setTbuserId(param.getReqTbuserId());

        TbracketDto.CreateResDto createResDto = tbracketRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbracketfileUrls().size();i++){
            tbracketfileService.create(
                    TbracketfileDto.CreateReqDto.builder()
                            .tbracketId(createResDto.getId())
                            .type(param.getTbracketfileTypes().get(i))
                            .url(param.getTbracketfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbracketDto.CreateResDto update(TbracketDto.UpdateServDto param){
        Tbracket tbracket = tbracketRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin() && !tbracket.getTbuserId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }

        if(param.getDeleted() != null){
            tbracket.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbracket.setProcess(param.getProcess());
        }

        /*if(param.getTbuserId() != null){
            tbracket.setTbuserId(param.getTbuserId());
        }*/
        if(param.getTitle() != null){
            tbracket.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbracket.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbracket.setCountread(param.getCountread());
        }
        */
        tbracketRepository.save(tbracket);
        return tbracket.toCreateResDto();
    }
    public TbracketDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbracketDto.UpdateServDto newParam = TbracketDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbracketDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbracketDto.UpdateServDto newParam = TbracketDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbracketDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbracketDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbracketDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbracketDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbracket tbracket = tbracketRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbracket.setCountread(++countread);
        tbracketRepository.save(tbracket);

        //조회수 업데이트!!
        update(TbracketDto.UpdateServDto.builder().id(selectResDto.getId()).countread(++countread).isAdmin(true).build());
        return selectResDto;
    }

    public TbracketDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbracketDto.DetailResDto selectResDto = tbracketMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbracketfiles(
                tbracketfileService.list(TbracketfileDto.ListReqDto.builder().tbracketId(selectResDto.getId()).build())
        );
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbracketlikeService.exist(TbracketlikeDto.CreateReqDto.builder().tbracketId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        return selectResDto;
    }

    @Override
    public List<TbracketDto.DetailResDto> list(TbracketDto.ListServDto param){
        return detailList(tbracketMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbracketDto.PagedListServDto param){
        int[] returnSize = param.init(tbracketMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbracketMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbracketDto.DetailResDto> scrollList(TbracketDto.ScrollListServDto param){
        param.init();
        return detailList(tbracketMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbracketDto.DetailResDto> detailList(List<TbracketDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbracketDto.DetailResDto> newList = new ArrayList<>();
        for(TbracketDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
