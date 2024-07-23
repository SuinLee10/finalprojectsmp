package com.thc.smspr2.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/tbboard")
@Controller
public class TbboardController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "tbboard/" + page;
    }


    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
        return "tbboard/" + page;
    }
}
