package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchfileDto;

import java.util.List;

public interface TbmatchfileMapper {
    TbmatchfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbmatchfileDto.DetailResDto> list(TbmatchfileDto.ListReqDto param);

    List<TbmatchfileDto.DetailResDto> scrollList(TbmatchfileDto.ScrollListReqDto param);
    List<TbmatchfileDto.DetailResDto> pagedList(TbmatchfileDto.PagedListReqDto param);
    int pagedListCount(TbmatchfileDto.PagedListReqDto param);
}
