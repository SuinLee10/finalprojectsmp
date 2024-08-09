package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbgrantuser;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantuserDto;
import com.thc.smspr2.dto.TbgrantuserDto;
import com.thc.smspr2.exception.NoAuthorizationException;
import com.thc.smspr2.mapper.TbgrantuserMapper;
import com.thc.smspr2.repository.TbgrantuserRepository;
import com.thc.smspr2.service.TbgrantuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TbgrantuserServiceImpl implements TbgrantuserService {

    private final TbgrantuserRepository tbgrantuserRepository;
    private final TbgrantuserMapper tbgrantuserMapper;
    public TbgrantuserServiceImpl(
            TbgrantuserRepository tbgrantuserRepository
            , TbgrantuserMapper tbgrantuserMapper
    ) {
        this.tbgrantuserRepository = tbgrantuserRepository;
        this.tbgrantuserMapper = tbgrantuserMapper;
    }


    /**/

    @Override
    public TbgrantuserDto.CreateResDto create(TbgrantuserDto.CreateServDto param){
        TbgrantuserDto.CreateResDto createResDto = tbgrantuserRepository.save(param.toEntity()).toCreateResDto();
        return createResDto;
    }

    @Override
    public TbgrantuserDto.CreateResDto update(TbgrantuserDto.UpdateServDto param){
        Tbgrantuser tbgrantuser = tbgrantuserRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin()){
            throw new NoAuthorizationException("no auth");
        }
        if(param.getDeleted() != null){
            tbgrantuser.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbgrantuser.setProcess(param.getProcess());
        }
        if(param.getTbgrantId() != null){
            tbgrantuser.setTbgrantId(param.getTbgrantId());
        }
        if(param.getTbuserId() != null){
            tbgrantuser.setTbuserId(param.getTbuserId());
        }
        tbgrantuserRepository.save(tbgrantuser);
        return tbgrantuser.toCreateResDto();
    }
    public TbgrantuserDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbgrantuserDto.UpdateServDto newParam = TbgrantuserDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbgrantuserDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbgrantuserDto.UpdateServDto newParam = TbgrantuserDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbgrantuserDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbgrantuserDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbgrantuserDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbgrantuserDto.DetailResDto selectResDto = get(param);
        return selectResDto;
    }

    public TbgrantuserDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbgrantuserDto.DetailResDto selectResDto = tbgrantuserMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbgrantuserDto.DetailResDto> list(TbgrantuserDto.ListServDto param){
        return detailList(tbgrantuserMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbgrantuserDto.PagedListServDto param){
        int[] returnSize = param.init(tbgrantuserMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbgrantuserMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbgrantuserDto.DetailResDto> scrollList(TbgrantuserDto.ScrollListServDto param){
        param.init();
        return detailList(tbgrantuserMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbgrantuserDto.DetailResDto> detailList(List<TbgrantuserDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbgrantuserDto.DetailResDto> newList = new ArrayList<>();
        for(TbgrantuserDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
