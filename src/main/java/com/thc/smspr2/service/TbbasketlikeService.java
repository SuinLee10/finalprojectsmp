package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketlikeDto;

import java.util.List;

public interface TbbasketlikeService {

    boolean exist(TbbasketlikeDto.CreateReqDto param);
    TbbasketlikeDto.CreateResDto toggle(TbbasketlikeDto.CreateReqDto param);
    /**/
    TbbasketlikeDto.CreateResDto create(TbbasketlikeDto.CreateReqDto param);
    TbbasketlikeDto.CreateResDto update(TbbasketlikeDto.UpdateReqDto param);
    TbbasketlikeDto.CreateResDto delete(TbbasketlikeDto.UpdateReqDto param);
    TbbasketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbbasketlikeDto.DetailResDto> list(TbbasketlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbbasketlikeDto.PagedListReqDto param);
    List<TbbasketlikeDto.DetailResDto> scrollList(TbbasketlikeDto.ScrollListReqDto param);
}
