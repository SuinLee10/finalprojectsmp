package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketfileDto;
import com.thc.smspr2.service.TbracketfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_1. 매칭 첨부파일 API 안내",
        description = "매칭 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbracketfile")
@RestController
public class TbracketfileRestController {

    private final TbracketfileService tbracketfileService;
    public TbracketfileRestController(TbracketfileService tbracketfileService) {
        this.tbracketfileService = tbracketfileService;
    }


    /**/

    @Operation(summary = "매칭 첨부파일 생성",
            description = "매칭 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbracketfileDto.CreateReqDto <br />"
                    + "@return Htt칭pStatus.CREATED(201) ResponseEntity\\<TbracketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbracketfileDto.CreateResDto> create(@Valid @RequestBody TbracketfileDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbracketfileService.create(param));
    }

    @Operation(summary = "매칭 첨부파일 수정",
            description = "매칭 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbracketfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbracketfileDto.CreateResDto> update(@Valid @RequestBody TbracketfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.update(param));
    }
    
    @Operation(summary = "매칭 첨부파일 삭제",
            description = "매칭 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbracketfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbracketfileDto.CreateResDto> delete(@Valid @RequestBody TbracketfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.delete(param));
    }

    @Operation(summary = "매칭 첨부파일 상세 조회",
            description = "매칭 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbracketfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbracketfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.detail(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 전체 조회",
            description = "매칭 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbracketfileDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbracketfileDto.DetailResDto>> list(@Valid TbracketfileDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.list(param));
    }

    @Operation(summary = "매칭 첨부파일 목록 페이지 조회",
            description = "매칭 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbracketfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbracketfileDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.pagedList(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 스크롤 조회",
            description = "매칭 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbracketfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbracketfileDto.DetailResDto>> mlist(@Valid TbracketfileDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketfileService.scrollList(param));
    }

}
