package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchlikeDto;

import java.util.List;

public interface TbmatchlikeService {

    boolean exist(TbmatchlikeDto.CreateReqDto param);
    TbmatchlikeDto.CreateResDto toggle(TbmatchlikeDto.CreateReqDto param);
    /**/
    TbmatchlikeDto.CreateResDto create(TbmatchlikeDto.CreateReqDto param);
    TbmatchlikeDto.CreateResDto update(TbmatchlikeDto.UpdateReqDto param);
    TbmatchlikeDto.CreateResDto delete(TbmatchlikeDto.UpdateReqDto param);
    TbmatchlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbmatchlikeDto.DetailResDto> list(TbmatchlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbmatchlikeDto.PagedListReqDto param);
    List<TbmatchlikeDto.DetailResDto> scrollList(TbmatchlikeDto.ScrollListReqDto param);
}
