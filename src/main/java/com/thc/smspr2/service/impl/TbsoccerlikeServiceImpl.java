package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbsoccerlike;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerlikeDto;
import com.thc.smspr2.mapper.TbsoccerlikeMapper;
import com.thc.smspr2.repository.TbsoccerlikeRepository;
import com.thc.smspr2.service.TbsoccerlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbsoccerlikeServiceImpl implements TbsoccerlikeService {

    private final TbsoccerlikeRepository tbsoccerlikeRepository;
    private final TbsoccerlikeMapper tbsoccerlikeMapper;
    public TbsoccerlikeServiceImpl(
            TbsoccerlikeRepository tbsoccerlikeRepository
            ,TbsoccerlikeMapper tbsoccerlikeMapper
    ) {
        this.tbsoccerlikeRepository = tbsoccerlikeRepository;
        this.tbsoccerlikeMapper = tbsoccerlikeMapper;
    }

    @Override
    public boolean exist(TbsoccerlikeDto.CreateReqDto param){
        Tbsoccerlike tbsoccerlike = tbsoccerlikeRepository.findByTbsoccerIdAndTbuserId(param.getTbsoccerId(),param.getTbuserId());
        return tbsoccerlike != null;
    }
    @Override
    public TbsoccerlikeDto.CreateResDto toggle(TbsoccerlikeDto.CreateReqDto param){
        Tbsoccerlike tbsoccerlike = tbsoccerlikeRepository.findByTbsoccerIdAndTbuserId(param.getTbsoccerId(),param.getTbuserId());
        if(tbsoccerlike == null){
            return tbsoccerlikeRepository.save(param.toEntity()).toCreateResDto();
        } else {
            return delete(TbsoccerlikeDto.UpdateReqDto.builder().id(tbsoccerlike.getId()).build());
        }
    }

    /**/

    @Override
    public TbsoccerlikeDto.CreateResDto create(TbsoccerlikeDto.CreateReqDto param){
        return tbsoccerlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbsoccerlikeDto.CreateResDto update(TbsoccerlikeDto.UpdateReqDto param){
        Tbsoccerlike tbsoccerlike = tbsoccerlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbsoccerlike.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbsoccerlike.setProcess(param.getProcess());
        }
        tbsoccerlikeRepository.save(tbsoccerlike);
        return tbsoccerlike.toCreateResDto();
    }
    @Override
    public TbsoccerlikeDto.CreateResDto delete(TbsoccerlikeDto.UpdateReqDto param){
        Tbsoccerlike tbsoccerlike = tbsoccerlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbsoccerlikeRepository.delete(tbsoccerlike);
        return TbsoccerlikeDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbsoccerlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbsoccerlikeDto.DetailResDto selectResDto = tbsoccerlikeMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbsoccerlikeDto.DetailResDto> list(TbsoccerlikeDto.ListReqDto param){
        return detailList(tbsoccerlikeMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbsoccerlikeDto.PagedListReqDto param) {
        /*int[] res = param.init(tbsoccerlikeMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbsoccerlikeMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbsoccerlikeDto.DetailResDto> scrollList(TbsoccerlikeDto.ScrollListReqDto param){
        param.init();
        return detailList(tbsoccerlikeMapper.scrollList(param));
    }

    public List<TbsoccerlikeDto.DetailResDto> detailList(List<TbsoccerlikeDto.DetailResDto> list){
        List<TbsoccerlikeDto.DetailResDto> newList = new ArrayList<>();
        for(TbsoccerlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
