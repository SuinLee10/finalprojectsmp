package com.thc.smspr2.controller;

import com.thc.smspr2.domain.Tbnotice;
import com.thc.smspr2.dto.TbnoticeDto;
import com.thc.smspr2.repository.TbnoticeRepository;
import com.thc.smspr2.service.TbnoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/tbnotice")
@RestController
public class TbnoticeRestController {

    private final TbnoticeService tbnoticeService;
    public TbnoticeRestController(TbnoticeService tbnoticeService) {
        this.tbnoticeService = tbnoticeService;
    }

    @PostMapping("")
    public ResponseEntity<TbnoticeDto.CreateResDto> create(@RequestBody TbnoticeDto.CreateReqDto param) {
        return ResponseEntity.status(HttpStatus.CREATED).body(tbnoticeService.create(param));
    }

    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> list(@RequestParam Map<String, Object> param) {

        List<Map<String, Object>> list = tbnoticeService.list(param);

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("data", list);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
