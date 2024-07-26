package com.thc.smspr2.service;


import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostcmtDto;

import java.util.List;

public interface TbpostcmtService {

    /**/
    TbpostcmtDto.CreateResDto create(TbpostcmtDto.CreateReqDto param);
    TbpostcmtDto.CreateResDto update(TbpostcmtDto.UpdateReqDto param);
    TbpostcmtDto.CreateResDto delete(TbpostcmtDto.UpdateReqDto param);
    TbpostcmtDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<TbpostcmtDto.DetailResDto> list(TbpostcmtDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(TbpostcmtDto.PagedListReqDto param);
    List<TbpostcmtDto.DetailResDto> scrollList(TbpostcmtDto.ScrollListReqDto param);
}
