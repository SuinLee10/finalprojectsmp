package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketfileDto;
import com.thc.smspr2.service.TbbasketfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_1. 매칭 첨부파일 API 안내",
        description = "매칭 첨부파일 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbbasketfile")
@RestController
public class TbbasketfileRestController {

    private final TbbasketfileService tbbasketfileService;
    public TbbasketfileRestController(TbbasketfileService tbbasketfileService) {
        this.tbbasketfileService = tbbasketfileService;
    }


    /**/

    @Operation(summary = "매칭 첨부파일 생성",
            description = "매칭 첨부파일 생성 컨트롤러 <br />"
                    + "@param TbbasketfileDto.CreateReqDto <br />"
                    + "@return Htt칭pStatus.CREATED(201) ResponseEntity\\<TbbasketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbbasketfileDto.CreateResDto> create(@Valid @RequestBody TbbasketfileDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbbasketfileService.create(param));
    }

    @Operation(summary = "매칭 첨부파일 수정",
            description = "매칭 첨부파일 수정 컨트롤러 <br />"
                    + "@param TbbasketfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbbasketfileDto.CreateResDto> update(@Valid @RequestBody TbbasketfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.update(param));
    }
    
    @Operation(summary = "매칭 첨부파일 삭제",
            description = "매칭 첨부파일 삭제 컨트롤러 <br />"
                    + "@param TbbasketfileDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbbasketfileDto.CreateResDto> delete(@Valid @RequestBody TbbasketfileDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.delete(param));
    }

    @Operation(summary = "매칭 첨부파일 상세 조회",
            description = "매칭 첨부파일 상세 조회 컨트롤러 <br />"
                    + "@param TbbasketfileDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbbasketfileDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.detail(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 전체 조회",
            description = "매칭 첨부파일 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbbasketfileDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbbasketfileDto.DetailResDto>> list(@Valid TbbasketfileDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.list(param));
    }

    @Operation(summary = "매칭 첨부파일 목록 페이지 조회",
            description = "매칭 첨부파일 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbbasketfileDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbbasketfileDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.pagedList(param));
    }
    @Operation(summary = "매칭 첨부파일 목록 스크롤 조회",
            description = "매칭 첨부파일 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbbasketfileDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketfileDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbbasketfileDto.DetailResDto>> mlist(@Valid TbbasketfileDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketfileService.scrollList(param));
    }

}
