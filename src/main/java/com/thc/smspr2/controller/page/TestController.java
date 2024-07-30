package com.thc.smspr2.controller.page;

import com.thc.smspr2.util.FileUpload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

@RequestMapping("/test")
@Controller
public class TestController {
    @GetMapping("/{page}")
    public String page(@PathVariable String page){
        return "test/" + page;
    }
    @GetMapping("/{page}/{id}")
    public String page(@PathVariable String page, @PathVariable String id){
        return "test/" + page;
    }
}
