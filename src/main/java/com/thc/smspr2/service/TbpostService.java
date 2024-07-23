package com.thc.smspr2.service;


import com.thc.smspr2.dto.TbpostDto;

import java.util.List;

public interface TbpostService {
    TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param);
    TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param);
    TbpostDto.SelectResDto detail(TbpostDto.SelectReqDto param);
    List<TbpostDto.SelectResDto> list(TbpostDto.ListReqDto param);
    TbpostDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param);
    List<TbpostDto.SelectResDto> scrollList(TbpostDto.ScrollListReqDto param);
}
