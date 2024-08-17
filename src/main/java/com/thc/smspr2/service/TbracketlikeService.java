package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketlikeDto;

import java.util.List;

public interface TbracketlikeService {

    boolean exist(TbracketlikeDto.CreateReqDto param);
    TbracketlikeDto.CreateResDto toggle(TbracketlikeDto.CreateReqDto param);
    /**/
    TbracketlikeDto.CreateResDto create(TbracketlikeDto.CreateReqDto param);
    TbracketlikeDto.CreateResDto update(TbracketlikeDto.UpdateReqDto param);
    TbracketlikeDto.CreateResDto delete(TbracketlikeDto.UpdateReqDto param);
    TbracketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbracketlikeDto.DetailResDto> list(TbracketlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbracketlikeDto.PagedListReqDto param);
    List<TbracketlikeDto.DetailResDto> scrollList(TbracketlikeDto.ScrollListReqDto param);
}
