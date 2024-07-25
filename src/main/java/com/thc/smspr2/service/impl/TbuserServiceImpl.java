package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbuser;
import com.thc.smspr2.dto.TbuserDto;
import com.thc.smspr2.mapper.TbuserMapper;
import com.thc.smspr2.repository.TbuserRepository;
import com.thc.smspr2.service.TbuserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbuserServiceImpl implements TbuserService {

    private TbuserRepository tbuserRepository;
    private TbuserMapper tbuserMapper;
    public TbuserServiceImpl(
            TbuserRepository tbuserRepository
            ,TbuserMapper tbuserMapper
    ) {
        this.tbuserRepository = tbuserRepository;
        this.tbuserMapper = tbuserMapper;
    }


    @Override
    public TbuserDto.CreateResDto login(TbuserDto.LoginReqDto param){
        //1번 방법
        Tbuser tbuser = tbuserRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if(tbuser == null){ return TbuserDto.CreateResDto.builder().id("not matched").build(); }

        /*
        //2번 방법
        TbuserDto.SelectResDto selectResDto = tbuserMapper.login(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        //return TbuserDto.CreateResDto.builder().id(selectResDto.getId()).build();
        */
        return TbuserDto.CreateResDto.builder().id(tbuser.getId()).build();
    }

    @Override
    public TbuserDto.CreateResDto signup(TbuserDto.SignupReqDto param){
        TbuserDto.CreateReqDto newParam = TbuserDto.CreateReqDto.builder().username(param.getUsername()).password(param.getPassword()).build();
        return tbuserRepository.save(newParam.toEntity()).toCreateResDto();
    }

    /**/

    @Override
    public TbuserDto.CreateResDto create(TbuserDto.CreateReqDto param){
        return tbuserRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public TbuserDto.CreateResDto update(TbuserDto.UpdateReqDto param){
        Tbuser tbuser = tbuserRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getName() != null){
            tbuser.setName(param.getName());
        }
        if(param.getNick() != null){
            tbuser.setNick(param.getNick());
        }
        if(param.getPhone() != null){
            tbuser.setPhone(param.getPhone());
        }
        if(param.getGender() != null){
            tbuser.setGender(param.getGender());
        }
        if(param.getContent() != null){
            tbuser.setContent(param.getContent());
        }
        if(param.getImg() != null){
            tbuser.setImg(param.getImg());
        }
        tbuserRepository.save(tbuser);
        return tbuser.toCreateResDto();
    }

    @Override
    public TbuserDto.SelectResDto detail(TbuserDto.SelectReqDto param){
        TbuserDto.SelectResDto selectResDto = tbuserMapper.detail(param);
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        return selectResDto;
    }

    @Override
    public List<TbuserDto.SelectResDto> list(TbuserDto.ListReqDto param){
        List<TbuserDto.SelectResDto> list = tbuserMapper.list(param);
        //그냥 리스트만 넘기지 않고, detail 에서 상세 정보를 가져옴
        //return list;
        List<TbuserDto.SelectResDto> newList = new ArrayList<>();
        for(TbuserDto.SelectResDto each : list){
            newList.add(detail(TbuserDto.SelectReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }
    @Override
    public TbuserDto.PagedListResDto pagedList(TbuserDto.PagedListReqDto param){

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
        int listsize = tbuserMapper.pagedListCount(param);
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

        List<TbuserDto.SelectResDto> list = tbuserMapper.pagedList(param);
        List<TbuserDto.SelectResDto> newList = new ArrayList<>();
        for(TbuserDto.SelectResDto each : list){
            newList.add(detail(TbuserDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        TbuserDto.PagedListResDto returnVal =
                TbuserDto.PagedListResDto.builder()
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
    public List<TbuserDto.SelectResDto> scrollList(TbuserDto.ScrollListReqDto param){

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
        /*
        String cursor = param.getCursor();
        if(cursor == null || cursor.isEmpty()){
            if("created_at".equals(orderby) && "desc".equals(orderway)){
                cursor = "9999-12-31 23:59:59.999999";
                param.setCursor(cursor);
            }
        }
        */
        Integer perpage = param.getPerpage();
        if(perpage == null || perpage < 1){
            //한번에 조회할 글 갯수
            perpage = 10;
            param.setPerpage(perpage);
        }

        List<TbuserDto.SelectResDto> list = tbuserMapper.scrollList(param);
        List<TbuserDto.SelectResDto> newList = new ArrayList<>();
        for(TbuserDto.SelectResDto each : list){
            newList.add(detail(TbuserDto.SelectReqDto.builder().id(each.getId()).build()));
        }

        return newList;
    }
}
