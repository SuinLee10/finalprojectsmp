package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerlikeDto;

import java.util.List;

public interface TbsoccerlikeMapper {
    TbsoccerlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbsoccerlikeDto.DetailResDto> list(TbsoccerlikeDto.ListReqDto param);

    List<TbsoccerlikeDto.DetailResDto> scrollList(TbsoccerlikeDto.ScrollListReqDto param);
    List<TbsoccerlikeDto.DetailResDto> pagedList(TbsoccerlikeDto.PagedListReqDto param);
    int pagedListCount(TbsoccerlikeDto.PagedListReqDto param);
}
