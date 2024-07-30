package com.thc.smspr2.controller;

import com.thc.smspr2.dto.DefaultDto;
import com.thc.smspr2.util.FileUpload;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/test")
@RestController
public class TestRestController {

    //List<String> list_title = new ArrayList<String>();

    @GetMapping("/abc")
    public Map<String, Object> abc(@RequestParam Map<String, Object> param) {
        System.out.println(param);
        /*list_title.add(param.get("title") + "");

        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result", "200");
        returnValue.put("size", list_title.size());
        returnValue.put("list_title", list_title.toArray());*/



        return null;
    }
    @GetMapping("/def")
    public String def(@Valid String title) {
        System.out.println(title);
        return "ok";
    }

}
