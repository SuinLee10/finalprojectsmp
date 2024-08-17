package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketDto;

import java.util.List;

public interface TbracketMapper {
    TbracketDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbracketDto.DetailResDto> list(TbracketDto.ListServDto param);

    List<TbracketDto.DetailResDto> scrollList(TbracketDto.ScrollListServDto param);
    List<TbracketDto.DetailResDto> pagedList(TbracketDto.PagedListServDto param);
    int pagedListCount(TbracketDto.PagedListServDto param);
}
