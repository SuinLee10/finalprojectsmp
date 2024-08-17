package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketDto;

import java.util.List;

public interface TbbasketService {
    TbbasketDto.CreateResDto create(TbbasketDto.CreateServDto param);
    TbbasketDto.CreateResDto update(TbbasketDto.UpdateServDto param);
    TbbasketDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbbasketDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbbasketDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbbasketDto.DetailResDto> list(TbbasketDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbbasketDto.PagedListServDto param);
    List<TbbasketDto.DetailResDto> scrollList(TbbasketDto.ScrollListServDto param);
}
