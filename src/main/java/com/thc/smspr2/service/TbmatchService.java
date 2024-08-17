package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchDto;

import java.util.List;

public interface TbmatchService {
    TbmatchDto.CreateResDto create(TbmatchDto.CreateServDto param);
    TbmatchDto.CreateResDto update(TbmatchDto.UpdateServDto param);
    TbmatchDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbmatchDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbmatchDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbmatchDto.DetailResDto> list(TbmatchDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbmatchDto.PagedListServDto param);
    List<TbmatchDto.DetailResDto> scrollList(TbmatchDto.ScrollListServDto param);
}
