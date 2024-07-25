package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
    TbuserDto.SelectResDto login(TbuserDto.LoginReqDto param);
    /**/
    TbuserDto.SelectResDto detail(TbuserDto.SelectReqDto param);
    List<TbuserDto.SelectResDto> list(TbuserDto.ListReqDto param);

    List<TbuserDto.SelectResDto> scrollList(TbuserDto.ScrollListReqDto param);
    List<TbuserDto.SelectResDto> pagedList(TbuserDto.PagedListReqDto param);
    int pagedListCount(TbuserDto.PagedListReqDto param);
}
