package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketfileDto;

import java.util.List;

public interface TbracketfileMapper {
    TbracketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbracketfileDto.DetailResDto> list(TbracketfileDto.ListReqDto param);

    List<TbracketfileDto.DetailResDto> scrollList(TbracketfileDto.ScrollListReqDto param);
    List<TbracketfileDto.DetailResDto> pagedList(TbracketfileDto.PagedListReqDto param);
    int pagedListCount(TbracketfileDto.PagedListReqDto param);
}
