package com.thc.smspr2.service;


import com.thc.smspr2.dto.TbuserDto;

import java.util.List;

public interface TbuserService {

    TbuserDto.CreateResDto login(TbuserDto.LoginReqDto param);
    TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param);
    /**/
    TbuserDto.CreateResDto create(TbuserDto.CreateReqDto param);
    TbuserDto.CreateResDto update(TbuserDto.UpdateReqDto param);
    TbuserDto.SelectResDto detail(TbuserDto.SelectReqDto param);
    List<TbuserDto.SelectResDto> list(TbuserDto.ListReqDto param);
    TbuserDto.PagedListResDto pagedList(TbuserDto.PagedListReqDto param);
    List<TbuserDto.SelectResDto> scrollList(TbuserDto.ScrollListReqDto param);
}
