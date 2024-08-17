package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbsoccerlikeDto;
import com.thc.smspr2.service.TbsoccerlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_3. 매칭 좋아요 API 안내",
        description = "매칭 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbsoccerlike")
@RestController
public class TbsoccerlikeRestController {

    private final TbsoccerlikeService tbsoccerlikeService;
    public TbsoccerlikeRestController(TbsoccerlikeService tbsoccerlikeService) {
        this.tbsoccerlikeService = tbsoccerlikeService;
    }


    @Operation(summary = "매칭 좋아요 토글",
            description = "매칭 좋아요 토글 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbsoccerlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbsoccerlikeDto.CreateResDto> toggle(@Valid @RequestBody TbsoccerlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbsoccerlikeService.toggle(param));
    }

    /**/

    @Operation(summary = "매칭 좋아요 생성",
            description = "매칭 좋아요 생성 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbsoccerlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbsoccerlikeDto.CreateResDto> create(@Valid @RequestBody TbsoccerlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbsoccerlikeService.create(param));
    }

    @Operation(summary = "매칭 좋아요 수정",
            description = "매칭 좋아요 수정 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbsoccerlikeDto.CreateResDto> update(@Valid @RequestBody TbsoccerlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.update(param));
    }
    
    @Operation(summary = "매칭 좋아요 삭제",
            description = "매칭 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbsoccerlikeDto.CreateResDto> delete(@Valid @RequestBody TbsoccerlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.delete(param));
    }

    @Operation(summary = "매칭 좋아요 상세 조회",
            description = "매칭 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbsoccerlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.detail(param));
    }
    @Operation(summary = "매칭 좋아요 목록 전체 조회",
            description = "매칭 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbsoccerlikeDto.DetailResDto>> list(@Valid TbsoccerlikeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.list(param));
    }

    @Operation(summary = "매칭 좋아요 목록 페이지 조회",
            description = "매칭 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbsoccerlikeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.pagedList(param));
    }
    @Operation(summary = "매칭 좋아요 목록 스크롤 조회",
            description = "매칭 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbsoccerlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbsoccerlikeDto.DetailResDto>> mlist(@Valid TbsoccerlikeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerlikeService.scrollList(param));
    }

}
