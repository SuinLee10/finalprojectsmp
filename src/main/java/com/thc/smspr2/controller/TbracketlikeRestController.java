package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbracketlikeDto;
import com.thc.smspr2.service.TbracketlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_3. 매칭 좋아요 API 안내",
        description = "매칭 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbracketlike")
@RestController
public class TbracketlikeRestController {

    private final TbracketlikeService tbracketlikeService;
    public TbracketlikeRestController(TbracketlikeService tbracketlikeService) {
        this.tbracketlikeService = tbracketlikeService;
    }


    @Operation(summary = "매칭 좋아요 토글",
            description = "매칭 좋아요 토글 컨트롤러 <br />"
                    + "@param TbracketlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbracketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbracketlikeDto.CreateResDto> toggle(@Valid @RequestBody TbracketlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbracketlikeService.toggle(param));
    }

    /**/

    @Operation(summary = "매칭 좋아요 생성",
            description = "매칭 좋아요 생성 컨트롤러 <br />"
                    + "@param TbracketlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbracketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbracketlikeDto.CreateResDto> create(@Valid @RequestBody TbracketlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbracketlikeService.create(param));
    }

    @Operation(summary = "매칭 좋아요 수정",
            description = "매칭 좋아요 수정 컨트롤러 <br />"
                    + "@param TbracketlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbracketlikeDto.CreateResDto> update(@Valid @RequestBody TbracketlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.update(param));
    }
    
    @Operation(summary = "매칭 좋아요 삭제",
            description = "매칭 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbracketlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbracketlikeDto.CreateResDto> delete(@Valid @RequestBody TbracketlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.delete(param));
    }

    @Operation(summary = "매칭 좋아요 상세 조회",
            description = "매칭 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbracketlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbracketlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.detail(param));
    }
    @Operation(summary = "매칭 좋아요 목록 전체 조회",
            description = "매칭 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbracketlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbracketlikeDto.DetailResDto>> list(@Valid TbracketlikeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.list(param));
    }

    @Operation(summary = "매칭 좋아요 목록 페이지 조회",
            description = "매칭 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbracketlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbracketlikeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.pagedList(param));
    }
    @Operation(summary = "매칭 좋아요 목록 스크롤 조회",
            description = "매칭 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbracketlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbracketlikeDto.DetailResDto>> mlist(@Valid TbracketlikeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbracketlikeService.scrollList(param));
    }

}
