package com.nlb.auth2.app.controller;

import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableResourceServer
public class UserController {
    @GetMapping("/test")
    @ResponseBody
    public String test01(){
        return "test";
    }
}
