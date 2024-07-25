package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpost;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.mapper.TbpostMapper;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.service.TbpostService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbpostServiceImpl implements TbpostService {

    private final TbpostRepository tbpostRepository;
    private final TbpostMapper tbpostMapper;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            ,TbpostMapper tbpostMapper
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        return tbpostRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getAuthor() != null){
            tbpost.setAuthor(param.getAuthor());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.SelectResDto detail(DefaultDto.SelectReqDto param){
        TbpostDto.SelectResDto selectResDto = tbpostMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbpostDto.SelectResDto> list(TbpostDto.ListReqDto param){
        return detailList(tbpostMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param){
        int[] returnSize = param.init(tbpostMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbpostMapper.pagedList(param)));
    }
    @Override
    public List<TbpostDto.SelectResDto> scrollList(TbpostDto.ScrollListReqDto param){
        param.init();
        return detailList(tbpostMapper.scrollList(param));
    }
    //
    public List<TbpostDto.SelectResDto> detailList(List<TbpostDto.SelectResDto> list){
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for(TbpostDto.SelectResDto each : list){
            newList.add(detail(DefaultDto.SelectReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
