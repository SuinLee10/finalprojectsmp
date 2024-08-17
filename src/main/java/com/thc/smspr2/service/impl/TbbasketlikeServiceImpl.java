package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbbasketlike;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketlikeDto;
import com.thc.smspr2.mapper.TbbasketlikeMapper;
import com.thc.smspr2.repository.TbbasketlikeRepository;
import com.thc.smspr2.service.TbbasketlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbbasketlikeServiceImpl implements TbbasketlikeService {

    private final TbbasketlikeRepository tbbasketlikeRepository;
    private final TbbasketlikeMapper tbbasketlikeMapper;
    public TbbasketlikeServiceImpl(
            TbbasketlikeRepository tbbasketlikeRepository
            ,TbbasketlikeMapper tbbasketlikeMapper
    ) {
        this.tbbasketlikeRepository = tbbasketlikeRepository;
        this.tbbasketlikeMapper = tbbasketlikeMapper;
    }

    @Override
    public boolean exist(TbbasketlikeDto.CreateReqDto param){
        Tbbasketlike tbbasketlike = tbbasketlikeRepository.findByTbbasketIdAndTbuserId(param.getTbbasketId(),param.getTbuserId());
        return tbbasketlike != null;
    }
    @Override
    public TbbasketlikeDto.CreateResDto toggle(TbbasketlikeDto.CreateReqDto param){
        Tbbasketlike tbbasketlike = tbbasketlikeRepository.findByTbbasketIdAndTbuserId(param.getTbbasketId(),param.getTbuserId());
        if(tbbasketlike == null){
            return tbbasketlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbbasketlikeDto.UpdateReqDto.builder().id(tbbasketlike.getId()).build());
        }
    }

    /**/

    @Override
    public TbbasketlikeDto.CreateResDto create(TbbasketlikeDto.CreateReqDto param){
        return tbbasketlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbbasketlikeDto.CreateResDto update(TbbasketlikeDto.UpdateReqDto param){
        Tbbasketlike tbbasketlike = tbbasketlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbbasketlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbbasketlike.setProcess(param.getProcess());
        }
        tbbasketlikeRepository.save(tbbasketlike);
        return tbbasketlike.toCreateResDto();
    }
    @Override
    public TbbasketlikeDto.CreateResDto delete(TbbasketlikeDto.UpdateReqDto param){
        Tbbasketlike tbbasketlike = tbbasketlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbbasketlikeRepository.delete(tbbasketlike);
        return TbbasketlikeDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbbasketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbbasketlikeDto.DetailResDto selectResDto = tbbasketlikeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbbasketlikeDto.DetailResDto> list(TbbasketlikeDto.ListReqDto param){
        return detailList(tbbasketlikeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbbasketlikeDto.PagedListReqDto param) {
        /*int[] res = param.init(tbbasketlikeMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbbasketlikeMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbbasketlikeDto.DetailResDto> scrollList(TbbasketlikeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbbasketlikeMapper.scrollList(param));
    }

    public List<TbbasketlikeDto.DetailResDto> detailList(List<TbbasketlikeDto.DetailResDto> list){
        List<TbbasketlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbbasketlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
