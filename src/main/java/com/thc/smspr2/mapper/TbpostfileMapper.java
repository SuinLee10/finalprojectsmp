package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostfileDto;

import java.util.List;

public interface TbpostfileMapper {
    TbpostfileDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostfileDto.DetailResDto> list(TbpostfileDto.ListReqDto param);

    List<TbpostfileDto.DetailResDto> scrollList(TbpostfileDto.ScrollListReqDto param);
    List<TbpostfileDto.DetailResDto> pagedList(TbpostfileDto.PagedListReqDto param);
    int pagedListCount(TbpostfileDto.PagedListReqDto param);
}
