package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import java.util.List;
import java.util.Map;

public interface TbpostMapper {
    TbpostDto.DetailResDto detail(DefaultDto.DetailServDto param);
    List<TbpostDto.DetailResDto> list(TbpostDto.ListServDto param);

    List<TbpostDto.DetailResDto> scrollList(TbpostDto.ScrollListServDto param);
    List<TbpostDto.DetailResDto> pagedList(TbpostDto.PagedListServDto param);
    int pagedListCount(TbpostDto.PagedListServDto param);
}
