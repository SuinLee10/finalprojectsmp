package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantpartDto;
import com.thc.smspr2.dto.TbgrantpartDto;

import java.util.List;

public interface TbgrantpartMapper {
	TbgrantpartDto.DetailResDto detail(DefaultDto.DetailServDto param);
	List<TbgrantpartDto.DetailResDto> list(TbgrantpartDto.ListServDto param);

	List<TbgrantpartDto.DetailResDto> scrollList(TbgrantpartDto.ScrollListServDto param);
	List<TbgrantpartDto.DetailResDto> pagedList(TbgrantpartDto.PagedListServDto param);
	int pagedListCount(TbgrantpartDto.PagedListServDto param);
}