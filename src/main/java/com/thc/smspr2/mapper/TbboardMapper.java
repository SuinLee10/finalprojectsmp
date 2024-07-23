package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.TbboardDto;

import java.util.List;

public interface TbboardMapper {
    TbboardDto.SelectResDto detail(TbboardDto.SelectReqDto param);
    List<TbboardDto.SelectResDto> list(TbboardDto.ListReqDto param);

    List<TbboardDto.SelectResDto> scrollList(TbboardDto.ScrollListReqDto param);
    List<TbboardDto.SelectResDto> pagedList(TbboardDto.PagedListReqDto param);
    int pagedListCount(TbboardDto.PagedListReqDto param);
}
