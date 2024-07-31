package com.thc.smspr2.controller;

import com.thc.smspr2.domain.Tbnotice;
import com.thc.smspr2.repository.TbnoticeRepository;
import com.thc.smspr2.service.TbnoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> param) {
        System.out.println(param);
        Map<String, Object> returnValue = tbnoticeService.create(param);
        return returnValue;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {

        //List<Tbnotice> list = tbnoticeRepository.findAll();

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        //returnValue.put("data", list);

        return returnValue;
    }

}
