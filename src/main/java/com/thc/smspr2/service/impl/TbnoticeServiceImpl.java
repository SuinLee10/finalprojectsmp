package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbnotice;
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
    public TbnoticeServiceImpl(
            TbnoticeRepository tbnoticeRepository
    ) {
        this.tbnoticeRepository = tbnoticeRepository;
    }


    @Override
    public Map<String, Object> create(Map<String, Object> param){


        Tbnotice tbnotice = new Tbnotice();
        tbnotice.setId(param.get("id") + "");
        tbnotice.setTitle(param.get("title") + "");
        tbnotice.setContent(param.get("content") + "");

        tbnotice = tbnoticeRepository.save(tbnotice);

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("id", tbnotice.getId());

        return returnValue;
    }
    @Override
    public List<Tbnotice> list(Map<String, Object> param){
        //메퍼에 부탁해보세요!! 2024-08-01 까지!!
        return null;
    }
}
