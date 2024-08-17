package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbracketfile;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketfileDto;
import com.thc.smspr2.mapper.TbracketfileMapper;
import com.thc.smspr2.repository.TbracketfileRepository;
import com.thc.smspr2.service.TbracketfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbracketfileServiceImpl implements TbracketfileService {

    private final TbracketfileRepository tbracketfileRepository;
    private final TbracketfileMapper tbracketfileMapper;
    public TbracketfileServiceImpl(
            TbracketfileRepository tbracketfileRepository
            ,TbracketfileMapper tbracketfileMapper
    ) {
        this.tbracketfileRepository = tbracketfileRepository;
        this.tbracketfileMapper = tbracketfileMapper;
    }

    /**/

    @Override
    public TbracketfileDto.CreateResDto create(TbracketfileDto.CreateReqDto param){
        return tbracketfileRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbracketfileDto.CreateResDto update(TbracketfileDto.UpdateReqDto param){
        Tbracketfile tbracketfile = tbracketfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbracketfile.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbracketfile.setProcess(param.getProcess());
        }
        tbracketfileRepository.save(tbracketfile);
        return tbracketfile.toCreateResDto();
    }
    @Override
    public TbracketfileDto.CreateResDto delete(TbracketfileDto.UpdateReqDto param){
        Tbracketfile tbracketfile = tbracketfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbracketfileRepository.delete(tbracketfile);
        return TbracketfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbracketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbracketfileDto.DetailResDto selectResDto = tbracketfileMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbracketfileDto.DetailResDto> list(TbracketfileDto.ListReqDto param){
        return detailList(tbracketfileMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbracketfileDto.PagedListReqDto param) {
        /*int[] res = param.init(tbracketfileMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbracketfileMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbracketfileDto.DetailResDto> scrollList(TbracketfileDto.ScrollListReqDto param){
        param.init();
        return detailList(tbracketfileMapper.scrollList(param));
    }

    public List<TbracketfileDto.DetailResDto> detailList(List<TbracketfileDto.DetailResDto> list){
        List<TbracketfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbracketfileDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
