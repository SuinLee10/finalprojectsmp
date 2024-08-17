package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbbasket;
import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketDto;
import com.thc.smspr2.dto.TbbasketfileDto;
import com.thc.smspr2.dto.TbbasketlikeDto;
import com.thc.smspr2.exception.NoAuthorizationException;
import com.thc.smspr2.mapper.TbbasketMapper;
import com.thc.smspr2.repository.TbbasketRepository;
import com.thc.smspr2.service.TbbasketService;
import com.thc.smspr2.service.TbbasketfileService;
import com.thc.smspr2.service.TbbasketlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbbasketServiceImpl implements TbbasketService {

    private final TbbasketRepository tbbasketRepository;
    private final TbbasketMapper tbbasketMapper;
    private final TbbasketfileService tbbasketfileService;
    private final TbbasketlikeService tbbasketlikeService;
    public TbbasketServiceImpl(
            TbbasketRepository tbbasketRepository
            , TbbasketMapper tbbasketMapper
            , TbbasketfileService tbbasketfileService
            , TbbasketlikeService tbbasketlikeService
    ) {
        this.tbbasketRepository = tbbasketRepository;
        this.tbbasketMapper = tbbasketMapper;
        this.tbbasketfileService = tbbasketfileService;
        this.tbbasketlikeService = tbbasketlikeService;
    }

    @Override
    public TbbasketDto.CreateResDto create(TbbasketDto.CreateServDto param){
        //사용자 정보 강제 입력
        param.setTbuserId(param.getReqTbuserId());

        TbbasketDto.CreateResDto createResDto = tbbasketRepository.save(param.toEntity()).toCreateResDto();

        for(int i=0;i<param.getTbbasketfileUrls().size();i++){
            tbbasketfileService.create(
                    TbbasketfileDto.CreateReqDto.builder()
                            .tbbasketId(createResDto.getId())
                            .type(param.getTbbasketfileTypes().get(i))
                            .url(param.getTbbasketfileUrls().get(i))
                            .build()
            );
        }

        return createResDto;
    }

    @Override
    public TbbasketDto.CreateResDto update(TbbasketDto.UpdateServDto param){
        Tbbasket tbbasket = tbbasketRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(!param.isAdmin() && !tbbasket.getTbuserId().equals(param.getReqTbuserId())){
            throw new NoAuthorizationException("no auth");
        }

        if(param.getDeleted() != null){
            tbbasket.setDeleted(param.getDeleted());
        }
        if(param.getProcess() != null){
            tbbasket.setProcess(param.getProcess());
        }

        /*if(param.getTbuserId() != null){
            tbbasket.setTbuserId(param.getTbuserId());
        }*/
        if(param.getTitle() != null){
            tbbasket.setTitle(param.getTitle());
        }
        if(param.getContent() != null){
            tbbasket.setContent(param.getContent());
        }
        /*
        if(param.getCountread() != null){
            tbbasket.setCountread(param.getCountread());
        }
        */
        tbbasketRepository.save(tbbasket);
        return tbbasket.toCreateResDto();
    }
    public TbbasketDto.CreateResDto delete(DefaultDto.DeleteServDto param){
        TbbasketDto.UpdateServDto newParam = TbbasketDto.UpdateServDto.builder().id(param.getId()).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
        return update(newParam);
    }
    public TbbasketDto.CreateResDto deletes(DefaultDto.DeletesServDto param){
        int count = 0;
        for(String each : param.getIds()){
            TbbasketDto.UpdateServDto newParam = TbbasketDto.UpdateServDto.builder().id(each).deleted("Y").reqTbuserId(param.getReqTbuserId()).build();
            TbbasketDto.CreateResDto result = update(newParam);
            if(!(result.getId()).isEmpty()) {
                count++;
            }
        }
        return TbbasketDto.CreateResDto.builder().id(count + "").build();
    }

    @Override
    public TbbasketDto.DetailResDto detail(DefaultDto.DetailServDto param){
        TbbasketDto.DetailResDto selectResDto = get(param);

        int countread = selectResDto.getCountread();
        Tbbasket tbbasket = tbbasketRepository.findById(selectResDto.getId()).orElseThrow(()->new RuntimeException("no data"));
        tbbasket.setCountread(++countread);
        tbbasketRepository.save(tbbasket);

        //조회수 업데이트!!
        update(TbbasketDto.UpdateServDto.builder().id(selectResDto.getId()).countread(++countread).isAdmin(true).build());
        return selectResDto;
    }

    public TbbasketDto.DetailResDto get(DefaultDto.DetailServDto param){
        TbbasketDto.DetailResDto selectResDto = tbbasketMapper.detail(param);
        System.out.println(param.getId());
        if(selectResDto == null){ throw new RuntimeException("no data"); }
        selectResDto.setTbbasketfiles(
                tbbasketfileService.list(TbbasketfileDto.ListReqDto.builder().tbbasketId(selectResDto.getId()).build())
        );
        //좋아요 했는지 안했는지 좀 확인해보자!
        if(param.getReqTbuserId() != null){
            boolean liked = tbbasketlikeService.exist(TbbasketlikeDto.CreateReqDto.builder().tbbasketId(selectResDto.getId()).tbuserId(param.getReqTbuserId()).build());
            selectResDto.setLiked(liked);
        }
        return selectResDto;
    }

    @Override
    public List<TbbasketDto.DetailResDto> list(TbbasketDto.ListServDto param){
        return detailList(tbbasketMapper.list(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    @Override
    public DefaultDto.PagedListResDto pagedList(TbbasketDto.PagedListServDto param){
        int[] returnSize = param.init(tbbasketMapper.pagedListCount(param));
        return param.afterBuild(returnSize, detailList(tbbasketMapper.pagedList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
    }
    @Override
    public List<TbbasketDto.DetailResDto> scrollList(TbbasketDto.ScrollListServDto param){
        param.init();
        return detailList(tbbasketMapper.scrollList(param), DefaultDto.DetailServDto.builder().reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build());
    }
    //
    public List<TbbasketDto.DetailResDto> detailList(List<TbbasketDto.DetailResDto> list, DefaultDto.DetailServDto param){
        List<TbbasketDto.DetailResDto> newList = new ArrayList<>();
        for(TbbasketDto.DetailResDto each : list){
            newList.add(get(DefaultDto.DetailServDto.builder().id(each.getId()).reqTbuserId(param.getReqTbuserId()).isAdmin(param.isAdmin()).build()));
        }
        return newList;
    }
}
