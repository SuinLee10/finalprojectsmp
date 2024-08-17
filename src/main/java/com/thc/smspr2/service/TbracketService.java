package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketDto;

import java.util.List;

public interface TbracketService {
    TbracketDto.CreateResDto create(TbracketDto.CreateServDto param);
    TbracketDto.CreateResDto update(TbracketDto.UpdateServDto param);
    TbracketDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbracketDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbracketDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbracketDto.DetailResDto> list(TbracketDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbracketDto.PagedListServDto param);
    List<TbracketDto.DetailResDto> scrollList(TbracketDto.ScrollListServDto param);
}
