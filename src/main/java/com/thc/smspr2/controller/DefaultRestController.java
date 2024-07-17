package com.thc.smspr2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class DefaultRestController {
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}
