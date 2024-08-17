package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbracketDto;
import com.thc.smspr2.security.PrincipalDetails;
import com.thc.smspr2.service.TbgrantService;
import com.thc.smspr2.service.TbracketService;
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
@RequestMapping("/api/tbracket")
@RestController
public class TbracketRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbracket";

    private final TbracketService tbracketService;
    private final TbgrantService tbgrantService;
    public TbracketRestController(TbracketService tbracketService, TbgrantService tbgrantService) {
        this.tbracketService = tbracketService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "매칭 생성",
            description = "매칭 생성 컨트롤러 <br />"
                    + "@param TbracketDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbracketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbracketDto.CreateResDto> create(@Valid @RequestBody TbracketDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbracketDto.CreateServDto newParam = (TbracketDto.CreateServDto) TbracketDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbracketService.create(newParam));
    }

    @Operation(summary = "매칭 수정",
            description = "매칭 수정 컨트롤러 <br />"
                    + "@param TbracketDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbracketDto.CreateResDto> update(@Valid @RequestBody TbracketDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbracketDto.UpdateServDto newParam = (TbracketDto.UpdateServDto) TbracketDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.update(newParam));
    }

    @Operation(summary = "매칭 삭제",
            description = "매칭 삭제 컨트롤러 <br />"
                    + "@param TbracketDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbracketDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.delete(newParam));
    }
    @Operation(summary = "매칭 여러개 삭제",
            description = "매칭 여러개 삭제 컨트롤러 <br />"
                    + "@param TbracketDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbracketDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.deletes(newParam));
    }

    @Operation(summary = "매칭 상세 조회",
            description = "매칭 상세 조회 컨트롤러 <br />"
                    + "@param TbracketDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbracketDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.detail(newParam));
    }
    @Operation(summary = "매칭 목록 전체 조회",
            description = "매칭 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbracketDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbracketDto.DetailResDto>> list(@Valid TbracketDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbracketDto.ListServDto newParam = (TbracketDto.ListServDto) TbracketDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.list(newParam));
    }

    @Operation(summary = "매칭 목록 페이지 조회",
            description = "매칭 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbracketDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbracketDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbracketDto.PagedListServDto newParam = (TbracketDto.PagedListServDto) TbracketDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.pagedList(newParam));
    }
    @Operation(summary = "매칭 목록 스크롤 조회",
            description = "매칭 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbracketDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbracketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbracketDto.DetailResDto>> mlist(@Valid TbracketDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbracketDto.ScrollListServDto newParam = (TbracketDto.ScrollListServDto) TbracketDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbracketService.scrollList(newParam));
    }

}
