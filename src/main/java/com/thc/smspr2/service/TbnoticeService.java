package com.thc.smspr2.service;

import com.thc.smspr2.dto.TbnoticeDto;

import java.util.List;
import java.util.Map;

public interface TbnoticeService {
    /**/
    TbnoticeDto.CreateResDto create(TbnoticeDto.CreateReqDto param);
    Map<String, Object> detail(Map<String, Object> param);
    List<Map<String, Object>> list(Map<String, Object> param);
}
