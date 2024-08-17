package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketlikeDto;

import java.util.List;

public interface TbbasketlikeMapper {
    TbbasketlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbbasketlikeDto.DetailResDto> list(TbbasketlikeDto.ListReqDto param);

    List<TbbasketlikeDto.DetailResDto> scrollList(TbbasketlikeDto.ScrollListReqDto param);
    List<TbbasketlikeDto.DetailResDto> pagedList(TbbasketlikeDto.PagedListReqDto param);
    int pagedListCount(TbbasketlikeDto.PagedListReqDto param);
}
