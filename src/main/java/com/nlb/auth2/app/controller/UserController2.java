package com.nlb.auth2.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController2 {
    @ResponseBody
    @GetMapping("/test2")
    public String test02(){
        return "test2";
    }
}
