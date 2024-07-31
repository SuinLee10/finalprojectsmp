package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpost;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.dto.TbpostfileDto;
import com.thc.smspr2.dto.TbpostlikeDto;
import com.thc.smspr2.mapper.TbpostMapper;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.service.TbpostService;
import com.thc.smspr2.service.TbpostfileService;
import com.thc.smspr2.service.TbpostlikeService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    private final TbpostfileService tbpostfileService;
    private final TbpostlikeService tbpostlikeService;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            , TbpostMapper tbpostMapper
            , TbpostfileService tbpostfileService
            , TbpostlikeService tbpostlikeService
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
        this.tbpostfileService = tbpostfileService;
        this.tbpostlikeService = tbpostlikeService;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        TbpostDto.CreateResDto createResDto = tbpostRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbpostfileUrls().size();i++){
            tbpostfileService.create(
                    TbpostfileDto.CreateReqDto.builder()
                            .tbpostId(createResDto.getId())
                            .type(param.getTbpostfileTypes().get(i))
                            .url(param.getTbpostfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbpost.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbpost.setProcess(param.getProcess());
        }

        if(param.getTbuserId() != null){
            tbpost.setTbuserId(param.getTbuserId());
        }
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbpost.setCountread(param.getCountread());
        }
        */
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbpostDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbpost tbpost = tbpostRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbpost.setCountread(++countread);
        tbpostRepository.save(tbpost);

        //update(TbpostDto.UpdateReqDto.builder().id(selectResDto.getId()).countread(++countread).build());
        return selectResDto;
    }

    public TbpostDto.DetailResDto get(DefaultDto.DetailReqDto param){
        TbpostDto.DetailResDto selectResDto = tbpostMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbpostfiles(
                tbpostfileService.list(TbpostfileDto.ListReqDto.builder().tbpostId(selectResDto.getId()).build())
        );

        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getTbuserId() != null){
            boolean liked = tbpostlikeService.exist(TbpostlikeDto.CreateReqDto.builder().tbpostId(selectResDto.getId()).tbuserId(param.getTbuserId()).build());
            selectResDto.setLiked(liked);
        }

        return selectResDto;
    }

    @Override
    public List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param){
        return detailList(tbpostMapper.list(param), null);
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param){
        int[] returnSize = param.init(tbpostMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbpostMapper.pagedList(param), null));
    }
    @Override
    public List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostMapper.scrollList(param), param.getTbuserId());
    }
    //
    public List<TbpostDto.DetailResDto> detailList(List<TbpostDto.DetailResDto> list, String tbuserId){
        List<TbpostDto.DetailResDto> newList = new ArrayList<>();
        for(TbpostDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailReqDto.builder().id(each.getId()).tbuserId(tbuserId).build()));
        }
        return newList;
    }
}
