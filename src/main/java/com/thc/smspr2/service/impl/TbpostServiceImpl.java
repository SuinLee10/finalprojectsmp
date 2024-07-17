package com.thc.smspr2.service.impl;

import com.thc.smspr2.domain.Tbpost;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.repository.TbpostRepository;
import com.thc.smspr2.service.TbpostService;
import org.springframework.stereotype.Service;

@Service
public class TbpostServiceImpl implements TbpostService {

    private TbpostRepository tbpostRepository;
    public TbpostServiceImpl(TbpostRepository tbpostRepository) {
        this.tbpostRepository = tbpostRepository;
    }

    public TbpostDto.CreateResDto create(TbpostDto.CreateReqDto param){
        //1번 방법
        /*Tbpost tbpost = new Tbpost();
        tbpost.setTitle(param.getTitle());
        tbpost.setAuthor(param.getAuthor());
        tbpost.setContent(param.getContent());

        tbpostRepository.save(tbpost);*/

        //2번 방법
        Tbpost tbpost = tbpostRepository.save(param.toEntity());

        /*
        //리턴 1번 방법
        TbpostDto.CreateResDto createResDto1 = new TbpostDto.CreateResDto();
        createResDto1.setId(tbpost.getId());

        //리턴 2번 방법
        TbpostDto.CreateResDto createResDto = tbpost.toCreateResDto();
        */
        return tbpost.toCreateResDto();
    }

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

    public TbpostDto.SelectResDto detail(TbpostDto.SelectReqDto param){
        Tbpost tbpost = tbpostRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        TbpostDto.SelectResDto selectResDto = TbpostDto.SelectResDto.builder()
                .id(tbpost.getId())
                .createdAt(tbpost.getCreatedAt() + "")
                .deleted(tbpost.getDeleted())
                .title(tbpost.getTitle())
                .author(tbpost.getAuthor())
                .content(tbpost.getContent())
                .build();
        return selectResDto;
    }
}
