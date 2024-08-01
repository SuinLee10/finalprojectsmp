package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbnotice;
import com.thc.smspr2.mapper.TbnoticeMapper;
import com.thc.smspr2.repository.TbnoticeRepository;
import com.thc.smspr2.service.TbnoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbnoticeServiceImpl implements TbnoticeService {

    private final TbnoticeRepository tbnoticeRepository;
    private final TbnoticeMapper tbnoticeMapper;
    public TbnoticeServiceImpl(
            TbnoticeRepository tbnoticeRepository,
            TbnoticeMapper tbnoticeMapper
    ) {
        this.tbnoticeRepository = tbnoticeRepository;
        this.tbnoticeMapper = tbnoticeMapper;
    }


    @Override
    public Map<String, Object> create(Map<String, Object> param){


        Tbnotice tbnotice = new Tbnotice();
        //tbnotice.setId(param.get("id") + "");
        tbnotice.setTitle(param.get("title") + "");
        tbnotice.setContent(param.get("content") + "");

        tbnotice = tbnoticeRepository.save(tbnotice);

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("id", tbnotice.getId());

        return returnValue;
    }
    @Override
    public Map<String, Object> detail(Map<String, Object> param){
        //메퍼에 부탁해보세요!! 2024-08-01 까지!!
        Map<String, Object> map_tbnotice = tbnoticeMapper.detail(param);
        return map_tbnotice;
    }
    @Override
    public List<Map<String, Object>> list(Map<String, Object> param){

        //검색어도 한번 넣어봅시다.
        /*param.put("title", "123");*/

        List<Map<String, Object>> list_tbnotice = tbnoticeMapper.list(param);
        return list_tbnotice;
    }
}
