package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbmatchDto;
import com.thc.smspr2.security.PrincipalDetails;
import com.thc.smspr2.service.TbgrantService;
import com.thc.smspr2.service.TbmatchService;
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
@RequestMapping("/api/tbmatch")
@RestController
public class TbmatchRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbmatch";

    private final TbmatchService tbmatchService;
    private final TbgrantService tbgrantService;
    public TbmatchRestController(TbmatchService tbmatchService, TbgrantService tbgrantService) {
        this.tbmatchService = tbmatchService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "매칭 생성",
            description = "매칭 생성 컨트롤러 <br />"
                    + "@param TbmatchDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbmatchDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbmatchDto.CreateResDto> create(@Valid @RequestBody TbmatchDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbmatchDto.CreateServDto newParam = (TbmatchDto.CreateServDto) TbmatchDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbmatchService.create(newParam));
    }

    @Operation(summary = "매칭 수정",
            description = "매칭 수정 컨트롤러 <br />"
                    + "@param TbmatchDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbmatchDto.CreateResDto> update(@Valid @RequestBody TbmatchDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbmatchDto.UpdateServDto newParam = (TbmatchDto.UpdateServDto) TbmatchDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.update(newParam));
    }

    @Operation(summary = "매칭 삭제",
            description = "매칭 삭제 컨트롤러 <br />"
                    + "@param TbmatchDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbmatchDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.delete(newParam));
    }
    @Operation(summary = "매칭 여러개 삭제",
            description = "매칭 여러개 삭제 컨트롤러 <br />"
                    + "@param TbmatchDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbmatchDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.deletes(newParam));
    }

    @Operation(summary = "매칭 상세 조회",
            description = "매칭 상세 조회 컨트롤러 <br />"
                    + "@param TbmatchDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbmatchDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.detail(newParam));
    }
    @Operation(summary = "매칭 목록 전체 조회",
            description = "매칭 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbmatchDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbmatchDto.DetailResDto>> list(@Valid TbmatchDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbmatchDto.ListServDto newParam = (TbmatchDto.ListServDto) TbmatchDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.list(newParam));
    }

    @Operation(summary = "매칭 목록 페이지 조회",
            description = "매칭 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbmatchDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbmatchDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbmatchDto.PagedListServDto newParam = (TbmatchDto.PagedListServDto) TbmatchDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.pagedList(newParam));
    }
    @Operation(summary = "매칭 목록 스크롤 조회",
            description = "매칭 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbmatchDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbmatchDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbmatchDto.DetailResDto>> mlist(@Valid TbmatchDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbmatchDto.ScrollListServDto newParam = (TbmatchDto.ScrollListServDto) TbmatchDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbmatchService.scrollList(newParam));
    }

}
