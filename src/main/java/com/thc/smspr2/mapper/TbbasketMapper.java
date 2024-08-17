package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketDto;

import java.util.List;

public interface TbbasketMapper {
    TbbasketDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbbasketDto.DetailResDto> list(TbbasketDto.ListServDto param);

    List<TbbasketDto.DetailResDto> scrollList(TbbasketDto.ScrollListServDto param);
    List<TbbasketDto.DetailResDto> pagedList(TbbasketDto.PagedListServDto param);
    int pagedListCount(TbbasketDto.PagedListServDto param);
}
