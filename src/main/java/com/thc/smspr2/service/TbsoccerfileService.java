package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerfileDto;

import java.util.List;

public interface TbsoccerfileService {

    /**/
    TbsoccerfileDto.CreateResDto create(TbsoccerfileDto.CreateReqDto param);
    TbsoccerfileDto.CreateResDto update(TbsoccerfileDto.UpdateReqDto param);
    TbsoccerfileDto.CreateResDto delete(TbsoccerfileDto.UpdateReqDto param);
    TbsoccerfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbsoccerfileDto.DetailResDto> list(TbsoccerfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbsoccerfileDto.PagedListReqDto param);
    List<TbsoccerfileDto.DetailResDto> scrollList(TbsoccerfileDto.ScrollListReqDto param);
}
