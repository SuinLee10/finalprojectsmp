package com.thc.smspr2.service;


import com.thc.smspr2.dto.TbboardDto;

import java.util.List;

public interface TbboardService {
    TbboardDto.CreateResDto create(TbboardDto.CreateReqDto param);
    TbboardDto.CreateResDto update(TbboardDto.UpdateReqDto param);
    TbboardDto.SelectResDto detail(TbboardDto.SelectReqDto param);
    List<TbboardDto.SelectResDto> list(TbboardDto.ListReqDto param);
    TbboardDto.PagedListResDto pagedList(TbboardDto.PagedListReqDto param);
    List<TbboardDto.SelectResDto> scrollList(TbboardDto.ScrollListReqDto param);
}
