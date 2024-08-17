package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbmatchlikeDto;
import com.thc.smspr2.service.TbmatchlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_3. 매칭 좋아요 API 안내",
        description = "매칭 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbmatchlike")
@RestController
public class TbmatchlikeRestController {

    private final TbmatchlikeService tbmatchlikeService;
    public TbmatchlikeRestController(TbmatchlikeService tbmatchlikeService) {
        this.tbmatchlikeService = tbmatchlikeService;
    }


    @Operation(summary = "매칭 좋아요 토글",
            description = "매칭 좋아요 토글 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbmatchlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbmatchlikeDto.CreateResDto> toggle(@Valid @RequestBody TbmatchlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbmatchlikeService.toggle(param));
    }

    /**/

    @Operation(summary = "매칭 좋아요 생성",
            description = "매칭 좋아요 생성 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbmatchlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbmatchlikeDto.CreateResDto> create(@Valid @RequestBody TbmatchlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbmatchlikeService.create(param));
    }

    @Operation(summary = "매칭 좋아요 수정",
            description = "매칭 좋아요 수정 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbmatchlikeDto.CreateResDto> update(@Valid @RequestBody TbmatchlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.update(param));
    }
    
    @Operation(summary = "매칭 좋아요 삭제",
            description = "매칭 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbmatchlikeDto.CreateResDto> delete(@Valid @RequestBody TbmatchlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.delete(param));
    }

    @Operation(summary = "매칭 좋아요 상세 조회",
            description = "매칭 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbmatchlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.detail(param));
    }
    @Operation(summary = "매칭 좋아요 목록 전체 조회",
            description = "매칭 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbmatchlikeDto.DetailResDto>> list(@Valid TbmatchlikeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.list(param));
    }

    @Operation(summary = "매칭 좋아요 목록 페이지 조회",
            description = "매칭 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbmatchlikeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.pagedList(param));
    }
    @Operation(summary = "매칭 좋아요 목록 스크롤 조회",
            description = "매칭 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbmatchlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbmatchlikeDto.DetailResDto>> mlist(@Valid TbmatchlikeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchlikeService.scrollList(param));
    }

}
