package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbgrantDto;
import com.thc.smspr2.dto.TbbasketDto;
import com.thc.smspr2.security.PrincipalDetails;
import com.thc.smspr2.service.TbgrantService;
import com.thc.smspr2.service.TbbasketService;
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
@RequestMapping("/api/tbbasket")
@RestController
public class TbbasketRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String tbgrantCate = "tbbasket";

    private final TbbasketService tbbasketService;
    private final TbgrantService tbgrantService;
    public TbbasketRestController(TbbasketService tbbasketService, TbgrantService tbgrantService) {
        this.tbbasketService = tbbasketService;
        this.tbgrantService = tbgrantService;
    }

    @Operation(summary = "매칭 생성",
            description = "매칭 생성 컨트롤러 <br />"
                    + "@param TbbasketDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbbasketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbbasketDto.CreateResDto> create(@Valid @RequestBody TbbasketDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        boolean isAdmin = tbgrantService.access(new TbgrantDto.AccessServDto(tbgrantCate, "create", principalDetails.getTbuser().getId()));
        TbbasketDto.CreateServDto newParam = (TbbasketDto.CreateServDto) TbbasketDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(isAdmin).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbbasketService.create(newParam));
    }

    @Operation(summary = "매칭 수정",
            description = "매칭 수정 컨트롤러 <br />"
                    + "@param TbbasketDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbbasketDto.CreateResDto> update(@Valid @RequestBody TbbasketDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbbasketDto.UpdateServDto newParam = (TbbasketDto.UpdateServDto) TbbasketDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.update(newParam));
    }

    @Operation(summary = "매칭 삭제",
            description = "매칭 삭제 컨트롤러 <br />"
                    + "@param TbbasketDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("")
    public ResponseEntity<TbbasketDto.CreateResDto> delete(@Valid @RequestBody DefaultDto.DeleteReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeleteServDto newParam = (DefaultDto.DeleteServDto) DefaultDto.DeleteServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.delete(newParam));
    }
    @Operation(summary = "매칭 여러개 삭제",
            description = "매칭 여러개 삭제 컨트롤러 <br />"
                    + "@param TbbasketDto.DeleteReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/list")
    public ResponseEntity<TbbasketDto.CreateResDto> deletes(@Valid @RequestBody DefaultDto.DeletesReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        DefaultDto.DeletesServDto newParam = (DefaultDto.DeletesServDto) DefaultDto.DeletesServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.deletes(newParam));
    }

    @Operation(summary = "매칭 상세 조회",
            description = "매칭 상세 조회 컨트롤러 <br />"
                    + "@param TbbasketDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbbasketDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.detail(newParam));
    }
    @Operation(summary = "매칭 목록 전체 조회",
            description = "매칭 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbbasketDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<TbbasketDto.DetailResDto>> list(@Valid TbbasketDto.ListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbbasketDto.ListServDto newParam = (TbbasketDto.ListServDto) TbbasketDto.ListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.list(newParam));
    }

    @Operation(summary = "매칭 목록 페이지 조회",
            description = "매칭 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbbasketDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbbasketDto.PagedListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbbasketDto.PagedListServDto newParam = (TbbasketDto.PagedListServDto) TbbasketDto.PagedListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.pagedList(newParam));
    }
    @Operation(summary = "매칭 목록 스크롤 조회",
            description = "매칭 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbbasketDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbbasketDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbbasketDto.DetailResDto>> mlist(@Valid TbbasketDto.ScrollListReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null; if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        TbbasketDto.ScrollListServDto newParam = (TbbasketDto.ScrollListServDto) TbbasketDto.ScrollListServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);
        return ResponseEntity.status(HttpStatus.OK).body(tbbasketService.scrollList(newParam));
    }

}
