package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketfileDto;

import java.util.List;

public interface TbbasketfileService {

    /**/
    TbbasketfileDto.CreateResDto create(TbbasketfileDto.CreateReqDto param);
    TbbasketfileDto.CreateResDto update(TbbasketfileDto.UpdateReqDto param);
    TbbasketfileDto.CreateResDto delete(TbbasketfileDto.UpdateReqDto param);
    TbbasketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbbasketfileDto.DetailResDto> list(TbbasketfileDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbbasketfileDto.PagedListReqDto param);
    List<TbbasketfileDto.DetailResDto> scrollList(TbbasketfileDto.ScrollListReqDto param);
}
