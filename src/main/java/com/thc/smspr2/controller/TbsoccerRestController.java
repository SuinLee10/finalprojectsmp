package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbsoccerDto;
import com.thc.smspr2.security.PrincipalDetails;
import com.thc.smspr2.service.TbgrantService;
import com.thc.smspr2.service.TbsoccerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1. 매칭 API 안내",
        description = "매칭 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbsoccer")
@RestController
public class TbsoccerRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbsoccer";

    private final TbsoccerService tbsoccerService;
    private final TbgrantService tbgrantService;
    public TbsoccerRestController(TbsoccerService tbsoccerService, TbgrantService tbgrantService) {
        this.tbsoccerService = tbsoccerService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "매칭 생성",
            description = "매칭 생성 컨트롤러 <br />"
                    + "@param TbsoccerDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbsoccerDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbsoccerDto.CreateResDto> create(@Valid @RequestBody TbsoccerDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbsoccerDto.CreateServDto newParam = (TbsoccerDto.CreateServDto) TbsoccerDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbsoccerService.create(newParam));
    }

    @Operation(summary = "매칭 수정",
            description = "매칭 수정 컨트롤러 <br />"
                    + "@param TbsoccerDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbsoccerDto.CreateResDto> update(@Valid @RequestBody TbsoccerDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbsoccerDto.UpdateServDto newParam = (TbsoccerDto.UpdateServDto) TbsoccerDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.update(newParam));
    }

    @Operation(summary = "매칭 삭제",
            description = "매칭 삭제 컨트롤러 <br />"
                    + "@param TbsoccerDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbsoccerDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.delete(newParam));
    }
    @Operation(summary = "매칭 여러개 삭제",
            description = "매칭 여러개 삭제 컨트롤러 <br />"
                    + "@param TbsoccerDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbsoccerDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.deletes(newParam));
    }

    @Operation(summary = "매칭 상세 조회",
            description = "매칭 상세 조회 컨트롤러 <br />"
                    + "@param TbsoccerDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbsoccerDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.detail(newParam));
    }
    @Operation(summary = "매칭 목록 전체 조회",
            description = "매칭 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbsoccerDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbsoccerDto.DetailResDto>> list(@Valid TbsoccerDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbsoccerDto.ListServDto newParam = (TbsoccerDto.ListServDto) TbsoccerDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.list(newParam));
    }

    @Operation(summary = "매칭 목록 페이지 조회",
            description = "매칭 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbsoccerDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbsoccerDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbsoccerDto.PagedListServDto newParam = (TbsoccerDto.PagedListServDto) TbsoccerDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.pagedList(newParam));
    }
    @Operation(summary = "매칭 목록 스크롤 조회",
            description = "매칭 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbsoccerDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbsoccerDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbsoccerDto.DetailResDto>> mlist(@Valid TbsoccerDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbsoccerDto.ScrollListServDto newParam = (TbsoccerDto.ScrollListServDto) TbsoccerDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbsoccerService.scrollList(newParam));
    }

}
