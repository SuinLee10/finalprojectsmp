package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbmatchlike;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchlikeDto;
import com.thc.smspr2.mapper.TbmatchlikeMapper;
import com.thc.smspr2.repository.TbmatchlikeRepository;
import com.thc.smspr2.service.TbmatchlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbmatchlikeServiceImpl implements TbmatchlikeService {

    private final TbmatchlikeRepository tbmatchlikeRepository;
    private final TbmatchlikeMapper tbmatchlikeMapper;
    public TbmatchlikeServiceImpl(
            TbmatchlikeRepository tbmatchlikeRepository
            ,TbmatchlikeMapper tbmatchlikeMapper
    ) {
        this.tbmatchlikeRepository = tbmatchlikeRepository;
        this.tbmatchlikeMapper = tbmatchlikeMapper;
    }

    @Override
    public boolean exist(TbmatchlikeDto.CreateReqDto param){
        Tbmatchlike tbmatchlike = tbmatchlikeRepository.findByTbmatchIdAndTbuserId(param.getTbmatchId(),param.getTbuserId());
        return tbmatchlike != null;
    }
    @Override
    public TbmatchlikeDto.CreateResDto toggle(TbmatchlikeDto.CreateReqDto param){
        Tbmatchlike tbmatchlike = tbmatchlikeRepository.findByTbmatchIdAndTbuserId(param.getTbmatchId(),param.getTbuserId());
        if(tbmatchlike == null){
            return tbmatchlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbmatchlikeDto.UpdateReqDto.builder().id(tbmatchlike.getId()).build());
        }
    }

    /**/

    @Override
    public TbmatchlikeDto.CreateResDto create(TbmatchlikeDto.CreateReqDto param){
        return tbmatchlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbmatchlikeDto.CreateResDto update(TbmatchlikeDto.UpdateReqDto param){
        Tbmatchlike tbmatchlike = tbmatchlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbmatchlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbmatchlike.setProcess(param.getProcess());
        }
        tbmatchlikeRepository.save(tbmatchlike);
        return tbmatchlike.toCreateResDto();
    }
    @Override
    public TbmatchlikeDto.CreateResDto delete(TbmatchlikeDto.UpdateReqDto param){
        Tbmatchlike tbmatchlike = tbmatchlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbmatchlikeRepository.delete(tbmatchlike);
        return TbmatchlikeDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbmatchlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbmatchlikeDto.DetailResDto selectResDto = tbmatchlikeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbmatchlikeDto.DetailResDto> list(TbmatchlikeDto.ListReqDto param){
        return detailList(tbmatchlikeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbmatchlikeDto.PagedListReqDto param) {
        /*int[] res = param.init(tbmatchlikeMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbmatchlikeMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbmatchlikeDto.DetailResDto> scrollList(TbmatchlikeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbmatchlikeMapper.scrollList(param));
    }

    public List<TbmatchlikeDto.DetailResDto> detailList(List<TbmatchlikeDto.DetailResDto> list){
        List<TbmatchlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbmatchlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
