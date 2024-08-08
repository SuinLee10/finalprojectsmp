package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;

import java.util.List;

public interface TbpostService {
    TbpostDto.CreateResDto create(TbpostDto.CreateServDto param);
    TbpostDto.CreateResDto update(TbpostDto.UpdateServDto param);
    TbpostDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbpostDto.DetailResDto> list(TbpostDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param);
    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListReqDto param);
}
