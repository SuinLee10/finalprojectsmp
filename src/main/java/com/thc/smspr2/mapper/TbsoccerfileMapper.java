package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerfileDto;

import java.util.List;

public interface TbsoccerfileMapper {
    TbsoccerfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbsoccerfileDto.DetailResDto> list(TbsoccerfileDto.ListReqDto param);

    List<TbsoccerfileDto.DetailResDto> scrollList(TbsoccerfileDto.ScrollListReqDto param);
    List<TbsoccerfileDto.DetailResDto> pagedList(TbsoccerfileDto.PagedListReqDto param);
    int pagedListCount(TbsoccerfileDto.PagedListReqDto param);
}
