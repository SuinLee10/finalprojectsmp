package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbboard;
import com.thc.smspr2.dto.TbboardDto;
import com.thc.smspr2.mapper.TbboardMapper;
import com.thc.smspr2.repository.TbboardRepository;
import com.thc.smspr2.service.TbboardService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbboardServiceImpl implements TbboardService {

    private TbboardRepository tbboardRepository;
    private TbboardMapper tbboardMapper;
    public TbboardServiceImpl(
            TbboardRepository tbboardRepository
            ,TbboardMapper tbboardMapper
    ) {
        this.tbboardRepository = tbboardRepository;
        this.tbboardMapper = tbboardMapper;
    }

    @Override
    public TbboardDto.CreateResDto create(TbboardDto.CreateReqDto param){
        return tbboardRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbboardDto.CreateResDto update(TbboardDto.UpdateReqDto param){
        Tbboard tbboard = tbboardRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getTitle() != null){
            tbboard.setTitle(param.getTitle());
        }
        if(param.getAuthor() != null){
            tbboard.setAuthor(param.getAuthor());
        }
        if(param.getContent() != null){
            tbboard.setContent(param.getContent());
        }
        tbboardRepository.save(tbboard);
        return tbboard.toCreateResDto();
    }

    @Override
    public TbboardDto.SelectResDto detail(TbboardDto.SelectReqDto param){
        //1번 방법
        /*
        Tbboard tbboard = tbboardRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        TbboardDto.SelectResDto selectResDto2 = TbboardDto.SelectResDto.builder()
                .id(tbboard.getId())
                .createdAt(tbboard.getCreatedAt() + "")
                .deleted(tbboard.getDeleted())
                .title(tbboard.getTitle())
                .author(tbboard.getAuthor())
                .content(tbboard.getContent())
                .build();
        */

        //2번 방법
        TbboardDto.SelectResDto selectResDto = tbboardMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbboardDto.SelectResDto> list(TbboardDto.ListReqDto param){
        List<TbboardDto.SelectResDto> list = tbboardMapper.list(param);
        //그냥 리스트만 넘기지 않고, detail 에서 상세 정보를 가져옴
        //return list;
        List<TbboardDto.SelectResDto> newList = new ArrayList<>();
        for(TbboardDto.SelectResDto each : list){
            newList.add(detail(TbboardDto.SelectReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
    @Override
    public TbboardDto.PagedListResDto pagedList(TbboardDto.PagedListReqDto param){

        String orderby = param.getOrderby();
        if(orderby == null || orderby.isEmpty()){
            orderby = "created_at";
        }
        String orderway = param.getOrderway();
        if(orderway == null || orderway.isEmpty()){
            orderway = "desc";
        }
        Integer perpage = param.getPerpage();
        if(perpage == null || perpage < 1){
            //한번에 조회할 글 갯수
            perpage = 10;
        }
        Integer callpage = param.getCallpage();
        if(callpage == null){
            //호출하는 페이지
            callpage = 1;
        }
        if(callpage < 1){
            callpage = 1;
        }

        //offset 을 계산하기 위해서는 전체 글 갯수가 필요합니다!
        int listsize = tbboardMapper.pagedListCount(param);
        /*
        총 글 등록 수 : 127 개
        총 페이지 수 : 13개 (10개씩 보는 기준)

        내가 2페이지를 호출한다면 몇번째 부터 보면 될까요?! 11번째 => 10(offset)
        */
        int pagesize = listsize / perpage;
        if(listsize % perpage > 0){
            pagesize++;
        }
        if(callpage > pagesize){
            callpage = pagesize;
        }
        int offset = (callpage - 1) * perpage;

        param.setOrderby(orderby);
        param.setOrderway(orderway);
        param.setOffset(offset);
        param.setPerpage(perpage);
        //1페이지일때 0
        //2페이지 일때 10

        List<TbboardDto.SelectResDto> list = tbboardMapper.pagedList(param);
        List<TbboardDto.SelectResDto> newList = new ArrayList<>();
        for(TbboardDto.SelectResDto each : list){
            newList.add(detail(TbboardDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        TbboardDto.PagedListResDto returnVal =
                TbboardDto.PagedListResDto.builder()
                        .callpage(callpage)
                        .perpage(perpage)
                        .orderby(orderby)
                        .orderway(orderway)
                        .listsize(listsize)
                        .pagesize(pagesize)
                        .list(newList)
                        .build();

        return returnVal;
    }

    @Override
    public List<TbboardDto.SelectResDto> scrollList(TbboardDto.ScrollListReqDto param){

        String orderby = param.getOrderby();
        if(orderby == null || orderby.isEmpty()){
            orderby = "created_at";
            param.setOrderby(orderby);
        }
        String orderway = param.getOrderway();
        if(orderway == null || orderway.isEmpty()){
            orderway = "desc";
            param.setOrderway(orderway);
        }
        //초기값 안줘도 정상 작동!
        /*String cursor = param.getCursor();
        if(cursor == null || cursor.isEmpty()){
            if("created_at".equals(orderby) && "desc".equals(orderway)){
                cursor = "9999-12-31 23:59:59.999999";
                param.setCursor(cursor);
            }
        }*/
        Integer perpage = param.getPerpage();
        if(perpage == null || perpage < 1){
            //한번에 조회할 글 갯수
            perpage = 10;
            param.setPerpage(perpage);
        }

        List<TbboardDto.SelectResDto> list = tbboardMapper.scrollList(param);
        List<TbboardDto.SelectResDto> newList = new ArrayList<>();
        for(TbboardDto.SelectResDto each : list){
            newList.add(detail(TbboardDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        return newList;
    }
}
