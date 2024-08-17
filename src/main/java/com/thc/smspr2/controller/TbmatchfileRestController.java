package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchfileDto;
import com.thc.smspr2.service.TbmatchfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_1. 매칭 첨부파일 API 안내",
        description = "매칭 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbmatchfile")
@RestController
public class TbmatchfileRestController {

    private final TbmatchfileService tbmatchfileService;
    public TbmatchfileRestController(TbmatchfileService tbmatchfileService) {
        this.tbmatchfileService = tbmatchfileService;
    }


    /**/

    @Operation(summary = "매칭 첨부파일 생성",
            description = "매칭 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbmatchfileDto.CreateReqDto <br />"
                    + "@return Htt칭pStatus.CREATED(201) ResponseEntity\\<TbmatchfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbmatchfileDto.CreateResDto> create(@Valid @RequestBody TbmatchfileDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbmatchfileService.create(param));
    }

    @Operation(summary = "매칭 첨부파일 수정",
            description = "매칭 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbmatchfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbmatchfileDto.CreateResDto> update(@Valid @RequestBody TbmatchfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.update(param));
    }
    
    @Operation(summary = "매칭 첨부파일 삭제",
            description = "매칭 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbmatchfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbmatchfileDto.CreateResDto> delete(@Valid @RequestBody TbmatchfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.delete(param));
    }

    @Operation(summary = "매칭 첨부파일 상세 조회",
            description = "매칭 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbmatchfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbmatchfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.detail(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 전체 조회",
            description = "매칭 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbmatchfileDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbmatchfileDto.DetailResDto>> list(@Valid TbmatchfileDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.list(param));
    }

    @Operation(summary = "매칭 첨부파일 목록 페이지 조회",
            description = "매칭 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbmatchfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbmatchfileDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.pagedList(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 스크롤 조회",
            description = "매칭 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbmatchfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbmatchfileDto.DetailResDto>> mlist(@Valid TbmatchfileDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchfileService.scrollList(param));
    }

}
