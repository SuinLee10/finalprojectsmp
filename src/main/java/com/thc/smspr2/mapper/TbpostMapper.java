package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import java.util.List;
import java.util.Map;

public interface TbpostMapper {
    TbpostDto.SelectResDto detail(DefaultDto.SelectReqDto param);
    List<TbpostDto.SelectResDto> list(TbpostDto.ListReqDto param);

    List<TbpostDto.SelectResDto> scrollList(TbpostDto.ScrollListReqDto param);
    List<TbpostDto.SelectResDto> pagedList(TbpostDto.PagedListReqDto param);
    int pagedListCount(TbpostDto.PagedListReqDto param);
}
