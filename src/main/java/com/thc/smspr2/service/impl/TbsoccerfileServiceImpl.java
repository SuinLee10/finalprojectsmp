package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbsoccerfile;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerfileDto;
import com.thc.smspr2.mapper.TbsoccerfileMapper;
import com.thc.smspr2.repository.TbsoccerfileRepository;
import com.thc.smspr2.service.TbsoccerfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbsoccerfileServiceImpl implements TbsoccerfileService {

    private final TbsoccerfileRepository tbsoccerfileRepository;
    private final TbsoccerfileMapper tbsoccerfileMapper;
    public TbsoccerfileServiceImpl(
            TbsoccerfileRepository tbsoccerfileRepository
            ,TbsoccerfileMapper tbsoccerfileMapper
    ) {
        this.tbsoccerfileRepository = tbsoccerfileRepository;
        this.tbsoccerfileMapper = tbsoccerfileMapper;
    }

    /**/

    @Override
    public TbsoccerfileDto.CreateResDto create(TbsoccerfileDto.CreateReqDto param){
        return tbsoccerfileRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbsoccerfileDto.CreateResDto update(TbsoccerfileDto.UpdateReqDto param){
        Tbsoccerfile tbsoccerfile = tbsoccerfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbsoccerfile.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbsoccerfile.setProcess(param.getProcess());
        }
        tbsoccerfileRepository.save(tbsoccerfile);
        return tbsoccerfile.toCreateResDto();
    }
    @Override
    public TbsoccerfileDto.CreateResDto delete(TbsoccerfileDto.UpdateReqDto param){
        Tbsoccerfile tbsoccerfile = tbsoccerfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbsoccerfileRepository.delete(tbsoccerfile);
        return TbsoccerfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbsoccerfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbsoccerfileDto.DetailResDto selectResDto = tbsoccerfileMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbsoccerfileDto.DetailResDto> list(TbsoccerfileDto.ListReqDto param){
        return detailList(tbsoccerfileMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbsoccerfileDto.PagedListReqDto param) {
        /*int[] res = param.init(tbsoccerfileMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbsoccerfileMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbsoccerfileDto.DetailResDto> scrollList(TbsoccerfileDto.ScrollListReqDto param){
        param.init();
        return detailList(tbsoccerfileMapper.scrollList(param));
    }

    public List<TbsoccerfileDto.DetailResDto> detailList(List<TbsoccerfileDto.DetailResDto> list){
        List<TbsoccerfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbsoccerfileDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
