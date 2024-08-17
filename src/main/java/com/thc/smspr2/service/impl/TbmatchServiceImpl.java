package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbmatch;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchDto;
import com.thc.smspr2.dto.TbmatchfileDto;
import com.thc.smspr2.dto.TbmatchlikeDto;
import com.thc.smspr2.exception.NoAuthorizationException;
import com.thc.smspr2.mapper.TbmatchMapper;
import com.thc.smspr2.repository.TbmatchRepository;
import com.thc.smspr2.service.TbmatchService;
import com.thc.smspr2.service.TbmatchfileService;
import com.thc.smspr2.service.TbmatchlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbmatchServiceImpl implements TbmatchService {

    private final TbmatchRepository tbmatchRepository;
    private final TbmatchMapper tbmatchMapper;
    private final TbmatchfileService tbmatchfileService;
    private final TbmatchlikeService tbmatchlikeService;
    public TbmatchServiceImpl(
            TbmatchRepository tbmatchRepository
            , TbmatchMapper tbmatchMapper
            , TbmatchfileService tbmatchfileService
            , TbmatchlikeService tbmatchlikeService
    ) {
        this.tbmatchRepository = tbmatchRepository;
        this.tbmatchMapper = tbmatchMapper;
        this.tbmatchfileService = tbmatchfileService;
        this.tbmatchlikeService = tbmatchlikeService;
    }

    @Override
    public TbmatchDto.CreateResDto create(TbmatchDto.CreateServDto param){
        //사용자 정보 강제 입력
        param.setTbuserId(param.getReqTbuserId());

        TbmatchDto.CreateResDto createResDto = tbmatchRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbmatchfileUrls().size();i++){
            tbmatchfileService.create(
                    TbmatchfileDto.CreateReqDto.builder()
                            .tbmatchId(createResDto.getId())
                            .type(param.getTbmatchfileTypes().get(i))
                            .url(param.getTbmatchfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbmatchDto.CreateResDto update(TbmatchDto.UpdateServDto param){
        Tbmatch tbmatch = tbmatchRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin() && !tbmatch.getTbuserId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }

        if(param.getDeleted() != null){
            tbmatch.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbmatch.setProcess(param.getProcess());
        }

        /*if(param.getTbuserId() != null){
            tbmatch.setTbuserId(param.getTbuserId());
        }*/
        if(param.getTitle() != null){
            tbmatch.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbmatch.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbmatch.setCountread(param.getCountread());
        }
        */
        tbmatchRepository.save(tbmatch);
        return tbmatch.toCreateResDto();
    }
    public TbmatchDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbmatchDto.UpdateServDto newParam = TbmatchDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbmatchDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbmatchDto.UpdateServDto newParam = TbmatchDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbmatchDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbmatchDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbmatchDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbmatchDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbmatch tbmatch = tbmatchRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbmatch.setCountread(++countread);
        tbmatchRepository.save(tbmatch);

        //조회수 업데이트!!
        update(TbmatchDto.UpdateServDto.builder().id(selectResDto.getId()).countread(++countread).isAdmin(true).build());
        return selectResDto;
    }

    public TbmatchDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbmatchDto.DetailResDto selectResDto = tbmatchMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbmatchfiles(
                tbmatchfileService.list(TbmatchfileDto.ListReqDto.builder().tbmatchId(selectResDto.getId()).build())
        );
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbmatchlikeService.exist(TbmatchlikeDto.CreateReqDto.builder().tbmatchId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        return selectResDto;
    }

    @Override
    public List<TbmatchDto.DetailResDto> list(TbmatchDto.ListServDto param){
        return detailList(tbmatchMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbmatchDto.PagedListServDto param){
        int[] returnSize = param.init(tbmatchMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbmatchMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbmatchDto.DetailResDto> scrollList(TbmatchDto.ScrollListServDto param){
        param.init();
        return detailList(tbmatchMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbmatchDto.DetailResDto> detailList(List<TbmatchDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbmatchDto.DetailResDto> newList = new ArrayList<>();
        for(TbmatchDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
