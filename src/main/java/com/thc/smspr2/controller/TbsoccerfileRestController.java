package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerfileDto;
import com.thc.smspr2.service.TbsoccerfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_1. 매칭 첨부파일 API 안내",
        description = "매칭 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbsoccerfile")
@RestController
public class TbsoccerfileRestController {

    private final TbsoccerfileService tbsoccerfileService;
    public TbsoccerfileRestController(TbsoccerfileService tbsoccerfileService) {
        this.tbsoccerfileService = tbsoccerfileService;
    }


    /**/

    @Operation(summary = "매칭 첨부파일 생성",
            description = "매칭 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.CreateReqDto <br />"
                    + "@return Htt칭pStatus.CREATED(201) ResponseEntity\\<TbsoccerfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbsoccerfileDto.CreateResDto> create(@Valid @RequestBody TbsoccerfileDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbsoccerfileService.create(param));
    }

    @Operation(summary = "매칭 첨부파일 수정",
            description = "매칭 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbsoccerfileDto.CreateResDto> update(@Valid @RequestBody TbsoccerfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.update(param));
    }
    
    @Operation(summary = "매칭 첨부파일 삭제",
            description = "매칭 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbsoccerfileDto.CreateResDto> delete(@Valid @RequestBody TbsoccerfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.delete(param));
    }

    @Operation(summary = "매칭 첨부파일 상세 조회",
            description = "매칭 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbsoccerfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.detail(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 전체 조회",
            description = "매칭 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbsoccerfileDto.DetailResDto>> list(@Valid TbsoccerfileDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.list(param));
    }

    @Operation(summary = "매칭 첨부파일 목록 페이지 조회",
            description = "매칭 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbsoccerfileDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.pagedList(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 스크롤 조회",
            description = "매칭 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbsoccerfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbsoccerfileDto.DetailResDto>> mlist(@Valid TbsoccerfileDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerfileService.scrollList(param));
    }

}
