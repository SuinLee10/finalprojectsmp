package com.thc.smspr2.controller;

import com.thc.smspr2.domain.Tbnotice;
import com.thc.smspr2.repository.TbnoticeRepository;
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

    private final TbnoticeRepository tbnoticeRepository;
    public TbnoticeRestController(TbnoticeRepository tbnoticeRepository) {
        this.tbnoticeRepository = tbnoticeRepository;
    }

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam Map<String, Object> param) {
        System.out.println(param);

        Tbnotice tbnotice = new Tbnotice();
        tbnotice.setId(param.get("id") + "");
        tbnotice.setTitle(param.get("title") + "");
        tbnotice.setContent(param.get("content") + "");

        tbnoticeRepository.save(tbnotice);

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");

        return returnValue;
    }

    @GetMapping("/list")
    public Map<String, Object> list() {

        List<Tbnotice> list = tbnoticeRepository.findAll();

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("data", list);

        return returnValue;
    }

}
