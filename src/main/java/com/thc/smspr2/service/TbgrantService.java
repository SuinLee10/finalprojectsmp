package com.thc.smspr2.service;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbgrantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TbgrantService {
    boolean access(TbgrantDto.AccessServDto param);
    /**/
    TbgrantDto.CreateResDto create(TbgrantDto.CreateServDto param);
    TbgrantDto.CreateResDto update(TbgrantDto.UpdateServDto param);
    TbgrantDto.CreateResDto delete(DefaultDto.DeleteServDto param);
    TbgrantDto.CreateResDto deletes(DefaultDto.DeletesServDto param);
    TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param);
    DefaultDto.PagedListResDto pagedList(TbgrantDto.PagedListServDto param);
    List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param);
}
