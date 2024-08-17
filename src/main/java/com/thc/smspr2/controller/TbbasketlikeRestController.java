package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbbasketlikeDto;
import com.thc.smspr2.service.TbbasketlikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1_3. 매칭 좋아요 API 안내",
        description = "매칭 좋아요 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbbasketlike")
@RestController
public class TbbasketlikeRestController {

    private final TbbasketlikeService tbbasketlikeService;
    public TbbasketlikeRestController(TbbasketlikeService tbbasketlikeService) {
        this.tbbasketlikeService = tbbasketlikeService;
    }


    @Operation(summary = "매칭 좋아요 토글",
            description = "매칭 좋아요 토글 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbbasketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("/toggle")
    public ResponseEntity<TbbasketlikeDto.CreateResDto> toggle(@Valid @RequestBody TbbasketlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbbasketlikeService.toggle(param));
    }

    /**/

    @Operation(summary = "매칭 좋아요 생성",
            description = "매칭 좋아요 생성 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbbasketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PostMapping("")
    public ResponseEntity<TbbasketlikeDto.CreateResDto> create(@Valid @RequestBody TbbasketlikeDto.CreateReqDto param){
        return ResponseEntity.status(HttpStatus.CREATED).body(tbbasketlikeService.create(param));
    }

    @Operation(summary = "매칭 좋아요 수정",
            description = "매칭 좋아요 수정 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PutMapping("")
    public ResponseEntity<TbbasketlikeDto.CreateResDto> update(@Valid @RequestBody TbbasketlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.update(param));
    }
    
    @Operation(summary = "매칭 좋아요 삭제",
            description = "매칭 좋아요 삭제 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @DeleteMapping("")
    public ResponseEntity<TbbasketlikeDto.CreateResDto> delete(@Valid @RequestBody TbbasketlikeDto.UpdateReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.delete(param));
    }

    @Operation(summary = "매칭 좋아요 상세 조회",
            description = "매칭 좋아요 상세 조회 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("")
    public ResponseEntity<TbbasketlikeDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.detail(param));
    }
    @Operation(summary = "매칭 좋아요 목록 전체 조회",
            description = "매칭 좋아요 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbbasketlikeDto.DetailResDto>> list(@Valid TbbasketlikeDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.list(param));
    }

    @Operation(summary = "매칭 좋아요 목록 페이지 조회",
            description = "매칭 좋아요 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbbasketlikeDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.pagedList(param));
    }
    @Operation(summary = "매칭 좋아요 목록 스크롤 조회",
            description = "매칭 좋아요 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbbasketlikeDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketlikeDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbbasketlikeDto.DetailResDto>> mlist(@Valid TbbasketlikeDto.ScrollListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketlikeService.scrollList(param));
    }

}
