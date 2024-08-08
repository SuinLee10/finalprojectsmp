package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.dto.TbpostDto;
import com.thc.smspr2.security.PrincipalDetails;
import com.thc.smspr2.service.TbpostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "1-1. 게시글 API 안내",
        description = "게시글 관련 기능 정의한 RestController.")
@RequestMapping("/api/tbpost")
@RestController
public class TbpostRestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TbpostService tbpostService;
    public TbpostRestController(TbpostService tbpostService) {
        this.tbpostService = tbpostService;
    }

    @Operation(summary = "게시글 생성",
            description = "게시글 생성 컨트롤러 <br />"
                    + "@param TbpostDto.CreateReqDto <br />"
                    + "@return HttpStatus.CREATED(201) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    //@PreAuthorize("permitAll()")
    @PostMapping("")
    public ResponseEntity<TbpostDto.CreateResDto> create(@Valid @RequestBody TbpostDto.CreateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        logger.info("tbuserId : " + principalDetails.getTbuser().getId());
        /*
        String reqTbuserId = request.getAttribute("reqTbuserId") + "";
        //인터셉터에서 토큰이 없었을 경우!
        if (request.getAttribute("reqTbuserId") == null) {
            //return null;
            throw new RuntimeException("should login");
        }
        //
        param.setTbuserId(reqTbuserId);
        */
        //param.setTbuserId(principalDetails.getTbuser().getId());
        //TbpostDto.CreateServDto newParam2 = TbpostDto.CreateServDto.builder().tbuserId(principalDetails.getTbuser().getId()).title(param.getTitle()).content(param.getContent()).build();
        //TbpostDto.CreateServDto newParam = (TbpostDto.CreateServDto) param.afterBuild(param);

        TbpostDto.CreateServDto newParam = (TbpostDto.CreateServDto) TbpostDto.CreateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.CREATED).body(tbpostService.create(newParam));
    }


    @Operation(summary = "게시글 수정",
            description = "게시글 수정 컨트롤러 <br />"
                    + "@param TbpostDto.UpdateReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.CreateResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("hasRole('USER')")
    @PutMapping("")
    public ResponseEntity<TbpostDto.CreateResDto> update(@Valid @RequestBody TbpostDto.UpdateReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        TbpostDto.UpdateServDto newParam = (TbpostDto.UpdateServDto) TbpostDto.UpdateServDto.builder().reqTbuserId(principalDetails.getTbuser().getId()).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.update(newParam));
    }

    @Operation(summary = "게시글 상세 조회",
            description = "게시글 상세 조회 컨트롤러 <br />"
                    + "@param TbpostDto.DetailReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @PreAuthorize("permitAll()")
    @GetMapping("")
    public ResponseEntity<TbpostDto.DetailResDto> detail(@Valid DefaultDto.DetailReqDto param, @AuthenticationPrincipal PrincipalDetails principalDetails){
        String reqTbuserId = null;
        if(principalDetails != null && principalDetails.getTbuser() != null){ reqTbuserId = principalDetails.getTbuser().getId(); }
        DefaultDto.DetailServDto newParam = (DefaultDto.DetailServDto) DefaultDto.DetailServDto.builder().reqTbuserId(reqTbuserId).isAdmin(false).build().afterBuild(param);

        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.detail(newParam));
    }
    @Operation(summary = "게시글 목록 전체 조회",
            description = "게시글 목록 전체 조회 컨트롤러 <br />"
                    + "@param TbpostDto.ListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/list")
    public ResponseEntity<List<TbpostDto.DetailResDto>> list(@Valid TbpostDto.ListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.list(param));
    }

    @Operation(summary = "게시글 목록 페이지 조회",
            description = "게시글 목록 페이지 조회 컨트롤러 <br />"
                    + "@param TbpostDto.PagedListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.PagedListResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/plist")
    public ResponseEntity<DefaultDto.PagedListResDto> plist(@Valid TbpostDto.PagedListReqDto param){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.pagedList(param));
    }
    @Operation(summary = "게시글 목록 스크롤 조회",
            description = "게시글 목록 스크롤 조회 컨트롤러 <br />"
                    + "@param TbpostDto.MoreListReqDto <br />"
                    + "@return HttpStatus.OK(200) ResponseEntity\\<TbpostDto.DetailResDto\\> <br />"
                    + "@exception 필수 파라미터 누락하였을 때 등 <br />"
    )
    @GetMapping("/mlist")
    public ResponseEntity<List<TbpostDto.DetailResDto>> mlist(@Valid TbpostDto.ScrollListReqDto param, HttpServletRequest request, HttpServletResponse response){
        return ResponseEntity.status(HttpStatus.OK).body(tbpostService.scrollList(param));
    }

}
