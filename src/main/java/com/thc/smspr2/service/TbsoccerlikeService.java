package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerlikeDto;

import java.util.List;

public interface TbsoccerlikeService {

    boolean exist(TbsoccerlikeDto.CreateReqDto param);
    TbsoccerlikeDto.CreateResDto toggle(TbsoccerlikeDto.CreateReqDto param);
    /**/
    TbsoccerlikeDto.CreateResDto create(TbsoccerlikeDto.CreateReqDto param);
    TbsoccerlikeDto.CreateResDto update(TbsoccerlikeDto.UpdateReqDto param);
    TbsoccerlikeDto.CreateResDto delete(TbsoccerlikeDto.UpdateReqDto param);
    TbsoccerlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbsoccerlikeDto.DetailResDto> list(TbsoccerlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbsoccerlikeDto.PagedListReqDto param);
    List<TbsoccerlikeDto.DetailResDto> scrollList(TbsoccerlikeDto.ScrollListReqDto param);
}
