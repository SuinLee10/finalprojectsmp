package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchDto;

import java.util.List;

public interface TbmatchMapper {
    TbmatchDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbmatchDto.DetailResDto> list(TbmatchDto.ListServDto param);

    List<TbmatchDto.DetailResDto> scrollList(TbmatchDto.ScrollListServDto param);
    List<TbmatchDto.DetailResDto> pagedList(TbmatchDto.PagedListServDto param);
    int pagedListCount(TbmatchDto.PagedListServDto param);
}
