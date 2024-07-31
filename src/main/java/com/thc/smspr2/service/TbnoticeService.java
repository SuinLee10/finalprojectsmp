package com.thc.smspr2.service;

import com.thc.smspr2.domain.Tbnotice;

import java.util.List;
import java.util.Map;

public interface TbnoticeService {
    /**/
    Map<String, Object> create(Map<String, Object> param);
    List<Tbnotice> list(Map<String, Object> param);
}
