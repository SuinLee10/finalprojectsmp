package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketlikeDto;

import java.util.List;

public interface TbracketlikeMapper {
    TbracketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbracketlikeDto.DetailResDto> list(TbracketlikeDto.ListReqDto param);

    List<TbracketlikeDto.DetailResDto> scrollList(TbracketlikeDto.ScrollListReqDto param);
    List<TbracketlikeDto.DetailResDto> pagedList(TbracketlikeDto.PagedListReqDto param);
    int pagedListCount(TbracketlikeDto.PagedListReqDto param);
}
