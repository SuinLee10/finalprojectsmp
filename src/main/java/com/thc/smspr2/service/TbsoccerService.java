package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerDto;

import java.util.List;

public interface TbsoccerService {
    TbsoccerDto.CreateResDto create(TbsoccerDto.CreateServDto param);
    TbsoccerDto.CreateResDto update(TbsoccerDto.UpdateServDto param);
    TbsoccerDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbsoccerDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbsoccerDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbsoccerDto.DetailResDto> list(TbsoccerDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbsoccerDto.PagedListServDto param);
    List<TbsoccerDto.DetailResDto> scrollList(TbsoccerDto.ScrollListServDto param);
}
