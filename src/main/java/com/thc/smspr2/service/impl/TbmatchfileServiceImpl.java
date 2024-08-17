package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbmatchfile;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchfileDto;
import com.thc.smspr2.mapper.TbmatchfileMapper;
import com.thc.smspr2.repository.TbmatchfileRepository;
import com.thc.smspr2.service.TbmatchfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbmatchfileServiceImpl implements TbmatchfileService {

    private final TbmatchfileRepository tbmatchfileRepository;
    private final TbmatchfileMapper tbmatchfileMapper;
    public TbmatchfileServiceImpl(
            TbmatchfileRepository tbmatchfileRepository
            ,TbmatchfileMapper tbmatchfileMapper
    ) {
        this.tbmatchfileRepository = tbmatchfileRepository;
        this.tbmatchfileMapper = tbmatchfileMapper;
    }

    /**/

    @Override
    public TbmatchfileDto.CreateResDto create(TbmatchfileDto.CreateReqDto param){
        return tbmatchfileRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbmatchfileDto.CreateResDto update(TbmatchfileDto.UpdateReqDto param){
        Tbmatchfile tbmatchfile = tbmatchfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbmatchfile.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbmatchfile.setProcess(param.getProcess());
        }
        tbmatchfileRepository.save(tbmatchfile);
        return tbmatchfile.toCreateResDto();
    }
    @Override
    public TbmatchfileDto.CreateResDto delete(TbmatchfileDto.UpdateReqDto param){
        Tbmatchfile tbmatchfile = tbmatchfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbmatchfileRepository.delete(tbmatchfile);
        return TbmatchfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbmatchfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbmatchfileDto.DetailResDto selectResDto = tbmatchfileMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbmatchfileDto.DetailResDto> list(TbmatchfileDto.ListReqDto param){
        return detailList(tbmatchfileMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbmatchfileDto.PagedListReqDto param) {
        /*int[] res = param.init(tbmatchfileMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbmatchfileMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbmatchfileDto.DetailResDto> scrollList(TbmatchfileDto.ScrollListReqDto param){
        param.init();
        return detailList(tbmatchfileMapper.scrollList(param));
    }

    public List<TbmatchfileDto.DetailResDto> detailList(List<TbmatchfileDto.DetailResDto> list){
        List<TbmatchfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbmatchfileDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
