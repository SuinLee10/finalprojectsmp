package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchlikeDto;

import java.util.List;

public interface TbmatchlikeMapper {
    TbmatchlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbmatchlikeDto.DetailResDto> list(TbmatchlikeDto.ListReqDto param);

    List<TbmatchlikeDto.DetailResDto> scrollList(TbmatchlikeDto.ScrollListReqDto param);
    List<TbmatchlikeDto.DetailResDto> pagedList(TbmatchlikeDto.PagedListReqDto param);
    int pagedListCount(TbmatchlikeDto.PagedListReqDto param);
}
