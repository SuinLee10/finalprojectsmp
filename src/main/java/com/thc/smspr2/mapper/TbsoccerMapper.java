package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerDto;

import java.util.List;

public interface TbsoccerMapper {
    TbsoccerDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbsoccerDto.DetailResDto> list(TbsoccerDto.ListServDto param);

    List<TbsoccerDto.DetailResDto> scrollList(TbsoccerDto.ScrollListServDto param);
    List<TbsoccerDto.DetailResDto> pagedList(TbsoccerDto.PagedListServDto param);
    int pagedListCount(TbsoccerDto.PagedListServDto param);
}
