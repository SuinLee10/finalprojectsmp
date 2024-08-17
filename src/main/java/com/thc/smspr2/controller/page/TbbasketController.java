package com.thc.smspr2.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbbasket")
@Controller
public class TbbasketController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "tbbasket/" + page;
    }


    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
        return "tbbasket/" + page;
    }
}
