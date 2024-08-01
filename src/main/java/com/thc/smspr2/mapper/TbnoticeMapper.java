package com.thc.smspr2.mapper;

import java.util.List;
import java.util.Map;

public interface TbnoticeMapper {
    Map<String, Object> detail(Map<String, Object> param);
    List<Map<String, Object>> list(Map<String, Object> param);

}
