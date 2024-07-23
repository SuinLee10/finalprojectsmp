package com.thc.smspr2.controller;

import com.thc.smspr2.dto.TbboardDto;
import com.thc.smspr2.service.TbboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1. 게시글 API 안내",
        description = "게시글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbboard")
@RestController
public class TbboardRestController {

    private TbboardService tbboardService;
    public TbboardRestController(TbboardService tbboardService) {
        this.tbboardService = tbboardService;
    }

    @Operation(summary = "게시글 생성",
            description = "게시글 생성 컨트롤러 <br />"
                    + "@param TbboardDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbboardDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbboardDto.CreateResDto> create(@Valid @RequestBody TbboardDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbboardService.create(param));
    }


    @Operation(summary = "게시글 수정",
            description = "게시글 수정 컨트롤러 <br />"
                    + "@param TbboardDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbboardDto.CreateResDto> update(@Valid @RequestBody TbboardDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.update(param));
    }

    @Operation(summary = "게시글 상세 조회",
            description = "게시글 상세 조회 컨트롤러 <br />"
                    + "@param TbboardDto.SelectReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardDto.SelectResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbboardDto.SelectResDto> detail(@Valid TbboardDto.SelectReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.detail(param));
    }
    @Operation(summary = "게시글 목록 전체 조회",
            description = "게시글 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbboardDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardDto.SelectResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbboardDto.SelectResDto>> list(@Valid TbboardDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.list(param));
    }

    @Operation(summary = "게시글 목록 페이지 조회",
            description = "게시글 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbboardDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<TbboardDto.PagedListResDto> plist(@Valid TbboardDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.pagedList(param));
    }
    @Operation(summary = "게시글 목록 스크롤 조회",
            description = "게시글 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbboardDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbboardDto.SelectResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbboardDto.SelectResDto>> mlist(@Valid TbboardDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbboardService.scrollList(param));
    }

}
