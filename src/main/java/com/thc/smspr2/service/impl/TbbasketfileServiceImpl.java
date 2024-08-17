package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbbasketfile;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketfileDto;
import com.thc.smspr2.mapper.TbbasketfileMapper;
import com.thc.smspr2.repository.TbbasketfileRepository;
import com.thc.smspr2.service.TbbasketfileService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbbasketfileServiceImpl implements TbbasketfileService {

    private final TbbasketfileRepository tbbasketfileRepository;
    private final TbbasketfileMapper tbbasketfileMapper;
    public TbbasketfileServiceImpl(
            TbbasketfileRepository tbbasketfileRepository
            ,TbbasketfileMapper tbbasketfileMapper
    ) {
        this.tbbasketfileRepository = tbbasketfileRepository;
        this.tbbasketfileMapper = tbbasketfileMapper;
    }

    /**/

    @Override
    public TbbasketfileDto.CreateResDto create(TbbasketfileDto.CreateReqDto param){
        return tbbasketfileRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbbasketfileDto.CreateResDto update(TbbasketfileDto.UpdateReqDto param){
        Tbbasketfile tbbasketfile = tbbasketfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){
            tbbasketfile.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbbasketfile.setProcess(param.getProcess());
        }
        tbbasketfileRepository.save(tbbasketfile);
        return tbbasketfile.toCreateResDto();
    }
    @Override
    public TbbasketfileDto.CreateResDto delete(TbbasketfileDto.UpdateReqDto param){
        Tbbasketfile tbbasketfile = tbbasketfileRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        //데이터를 계속 유지할 생각이 있다면..
        /*
        param.setDeleted("Y");
        return update(param);
        */

        //진짜 지우고 싶다면..
        tbbasketfileRepository.delete(tbbasketfile);
        return TbbasketfileDto.CreateResDto.builder().id("success").build();
    }

    @Override
    public TbbasketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param){
        TbbasketfileDto.DetailResDto selectResDto = tbbasketfileMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbbasketfileDto.DetailResDto> list(TbbasketfileDto.ListReqDto param){
        return detailList(tbbasketfileMapper.list(param));
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbbasketfileDto.PagedListReqDto param) {
        /*int[] res = param.init(tbbasketfileMapper.pagedListCount(param));
        return param.afterBuild(res, detailList(tbbasketfileMapper.pagedList(param)));*/
        return null;
    }
    @Override
    public List<TbbasketfileDto.DetailResDto> scrollList(TbbasketfileDto.ScrollListReqDto param){
        param.init();
        return detailList(tbbasketfileMapper.scrollList(param));
    }

    public List<TbbasketfileDto.DetailResDto> detailList(List<TbbasketfileDto.DetailResDto> list){
        List<TbbasketfileDto.DetailResDto> newList = new ArrayList<>();
        for(TbbasketfileDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
}
