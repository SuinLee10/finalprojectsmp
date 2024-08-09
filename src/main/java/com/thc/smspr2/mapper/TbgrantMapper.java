package com.thc.smspr2.mapper;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbgrantDto;

import java.util.List;

//2024-07-08 추가(클래스 처음 추가함)
public interface TbgrantMapper {
	TbgrantDto.DetailResDto access(TbgrantDto.AccessServDto param);
	/**/
	TbgrantDto.DetailResDto detail(DefaultDto.DetailServDto param);
	List<TbgrantDto.DetailResDto> list(TbgrantDto.ListServDto param);

	List<TbgrantDto.DetailResDto> scrollList(TbgrantDto.ScrollListServDto param);
	List<TbgrantDto.DetailResDto> pagedList(TbgrantDto.PagedListServDto param);
	int pagedListCount(TbgrantDto.PagedListServDto param);
}