package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtMapper {
    TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param);

    List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param);
    List<TbpostcmtDto.DetailResDto> pagedList(TbpostcmtDto.PagedListReqDto param);
    int pagedListCount(TbpostcmtDto.PagedListReqDto param);
}
