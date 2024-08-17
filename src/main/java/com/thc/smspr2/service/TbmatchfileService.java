package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchfileDto;

import java.util.List;

public interface TbmatchfileService {

    /**/
    TbmatchfileDto.CreateResDto create(TbmatchfileDto.CreateReqDto param);
    TbmatchfileDto.CreateResDto update(TbmatchfileDto.UpdateReqDto param);
    TbmatchfileDto.CreateResDto delete(TbmatchfileDto.UpdateReqDto param);
    TbmatchfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbmatchfileDto.DetailResDto> list(TbmatchfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbmatchfileDto.PagedListReqDto param);
    List<TbmatchfileDto.DetailResDto> scrollList(TbmatchfileDto.ScrollListReqDto param);
}
