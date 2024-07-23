package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpost;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.mapper.TbpostMapper;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.service.TbpostService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbpostServiceImpl implements TbpostService {

    private TbpostRepository tbpostRepository;
    private TbpostMapper tbpostMapper;
    public TbpostServiceImpl(
            TbpostRepository tbpostRepository
            ,TbpostMapper tbpostMapper
    ) {
        this.tbpostRepository = tbpostRepository;
        this.tbpostMapper = tbpostMapper;
    }

    @Override
    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        return tbpostRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbpostDto.CreateResDto update(TbpostDto.UpdateReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getTitle() != null){
            tbpost.setTitle(param.getTitle());
        }
        if(param.getAuthor() != null){
            tbpost.setAuthor(param.getAuthor());
        }
        if(param.getContent() != null){
            tbpost.setContent(param.getContent());
        }
        tbpostRepository.save(tbpost);
        return tbpost.toCreateResDto();
    }

    @Override
    public TbpostDto.SelectResDto detail(TbpostDto.SelectReqDto param){
        //1번 방법
        /*
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        TbpostDto.SelectResDto selectResDto2 = TbpostDto.SelectResDto.builder()
                .id(tbpost.getId())
                .createdAt(tbpost.getCreatedAt() + "")
                .deleted(tbpost.getDeleted())
                .title(tbpost.getTitle())
                .author(tbpost.getAuthor())
                .content(tbpost.getContent())
                .build();
        */

        //2번 방법
        TbpostDto.SelectResDto selectResDto = tbpostMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbpostDto.SelectResDto> list(TbpostDto.ListReqDto param){
        List<TbpostDto.SelectResDto> list = tbpostMapper.list(param);
        //그냥 리스트만 넘기지 않고, detail 에서 상세 정보를 가져옴
        //return list;
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for(TbpostDto.SelectResDto each : list){
            newList.add(detail(TbpostDto.SelectReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
    @Override
    public TbpostDto.PagedListResDto pagedList(TbpostDto.PagedListReqDto param){

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
        int listsize = tbpostMapper.pagedListCount(param);
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

        List<TbpostDto.SelectResDto> list = tbpostMapper.pagedList(param);
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for(TbpostDto.SelectResDto each : list){
            newList.add(detail(TbpostDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        TbpostDto.PagedListResDto returnVal =
                TbpostDto.PagedListResDto.builder()
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
    public List<TbpostDto.SelectResDto> scrollList(TbpostDto.ScrollListReqDto param){

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
        String cursor = param.getCursor();
        if(cursor == null || cursor.isEmpty()){
            if("created_at".equals(orderby) && "desc".equals(orderway)){
                cursor = "9999-12-31 23:59:59.999999";
                param.setCursor(cursor);
            }
        }
        Integer perpage = param.getPerpage();
        if(perpage == null || perpage < 1){
            //한번에 조회할 글 갯수
            perpage = 10;
            param.setPerpage(perpage);
        }

        System.out.println("00 orderby : " + orderby);
        System.out.println("01 orderby : " + param.getOrderby());
        System.out.println("00 orderway : " + orderway);
        System.out.println("00 cursor : " + cursor);


        List<TbpostDto.SelectResDto> list = tbpostMapper.scrollList(param);
        List<TbpostDto.SelectResDto> newList = new ArrayList<>();
        for(TbpostDto.SelectResDto each : list){
            newList.add(detail(TbpostDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        return newList;
    }
}
