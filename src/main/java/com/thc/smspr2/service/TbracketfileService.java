package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketfileDto;

import java.util.List;

public interface TbracketfileService {

    /**/
    TbracketfileDto.CreateResDto create(TbracketfileDto.CreateReqDto param);
    TbracketfileDto.CreateResDto update(TbracketfileDto.UpdateReqDto param);
    TbracketfileDto.CreateResDto delete(TbracketfileDto.UpdateReqDto param);
    TbracketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbracketfileDto.DetailResDto> list(TbracketfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbracketfileDto.PagedListReqDto param);
    List<TbracketfileDto.DetailResDto> scrollList(TbracketfileDto.ScrollListReqDto param);
}
