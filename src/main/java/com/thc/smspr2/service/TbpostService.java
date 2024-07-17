package com.thc.smspr2.service;


import com.thc.smspr2.dto.TbpostDto;

public interface TbpostService {
    TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param);
    TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param);
    TbpostDto.SelectResDto detail(TbpostDto.SelectReqDto param);
}
