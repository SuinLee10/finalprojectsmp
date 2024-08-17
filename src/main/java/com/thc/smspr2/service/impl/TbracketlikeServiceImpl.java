package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbracketlike;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketlikeDto;
import com.thc.smspr2.mapper.TbracketlikeMapper;
import com.thc.smspr2.repository.TbracketlikeRepository;
import com.thc.smspr2.service.TbracketlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbracketlikeServiceImpl implements TbracketlikeService {

    private final TbracketlikeRepository tbracketlikeRepository;
    private final TbracketlikeMapper tbracketlikeMapper;
    public TbracketlikeServiceImpl(
            TbracketlikeRepository tbracketlikeRepository
            ,TbracketlikeMapper tbracketlikeMapper
    ) {
        this.tbracketlikeRepository = tbracketlikeRepository;
        this.tbracketlikeMapper = tbracketlikeMapper;
    }

    @Override
    public boolean exist(TbracketlikeDto.CreateReqDto param){
        Tbracketlike tbracketlike = tbracketlikeRepository.findByTbracketIdAndTbuserId(param.getTbracketId(),param.getTbuserId());
        return tbracketlike != null;
    }
    @Override
    public TbracketlikeDto.CreateResDto toggle(TbracketlikeDto.CreateReqDto param){
        Tbracketlike tbracketlike = tbracketlikeRepository.findByTbracketIdAndTbuserId(param.getTbracketId(),param.getTbuserId());
        if(tbracketlike == null){
            return tbracketlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbracketlikeDto.UpdateReqDto.builder().id(tbracketlike.getId()).build());
        }
    }

    /**/

    @Override
    public TbracketlikeDto.CreateResDto create(TbracketlikeDto.CreateReqDto param){
        return tbracketlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbracketlikeDto.CreateResDto update(TbracketlikeDto.UpdateReqDto param){
        Tbracketlike tbracketlike = tbracketlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbracketlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbracketlike.setProcess(param.getProcess());
        }
        tbracketlikeRepository.save(tbracketlike);
        return tbracketlike.toCreateResDto();
    }
    @Override
    public TbracketlikeDto.CreateResDto delete(TbracketlikeDto.UpdateReqDto param){
        Tbracketlike tbracketlike = tbracketlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbracketlikeRepository.delete(tbracketlike);
        return TbracketlikeDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbracketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbracketlikeDto.DetailResDto selectResDto = tbracketlikeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbracketlikeDto.DetailResDto> list(TbracketlikeDto.ListReqDto param){
        return detailList(tbracketlikeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbracketlikeDto.PagedListReqDto param) {
        /*int[] res = param.init(tbracketlikeMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbracketlikeMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbracketlikeDto.DetailResDto> scrollList(TbracketlikeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbracketlikeMapper.scrollList(param));
    }

    public List<TbracketlikeDto.DetailResDto> detailList(List<TbracketlikeDto.DetailResDto> list){
        List<TbracketlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbracketlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
