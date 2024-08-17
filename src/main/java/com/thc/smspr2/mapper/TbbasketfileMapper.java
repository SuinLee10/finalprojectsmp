package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketfileDto;

import java.util.List;

public interface TbbasketfileMapper {
    TbbasketfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbbasketfileDto.DetailResDto> list(TbbasketfileDto.ListReqDto param);

    List<TbbasketfileDto.DetailResDto> scrollList(TbbasketfileDto.ScrollListReqDto param);
    List<TbbasketfileDto.DetailResDto> pagedList(TbbasketfileDto.PagedListReqDto param);
    int pagedListCount(TbbasketfileDto.PagedListReqDto param);
}
