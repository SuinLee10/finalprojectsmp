package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbuserDto;

import java.util.List;

public interface TbuserMapper {
    TbuserDto.DetailResDto login(TbuserDto.LoginReqDto param);
    /**/
    TbuserDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbuserDto.DetailResDto> list(TbuserDto.ListReqDto param);

    List<TbuserDto.DetailResDto> scrollList(TbuserDto.ScrollListReqDto param);
    List<TbuserDto.DetailResDto> pagedList(TbuserDto.PagedListReqDto param);
    int pagedListCount(TbuserDto.PagedListReqDto param);
}
