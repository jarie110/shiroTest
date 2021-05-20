package com.jarie.shirotest.controller;

import com.jarie.shirotest.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ShiroController {
    @Autowired
    ShiroService shiroService;
    @RequestMapping("/")
    public String toLogin(){
        return "login.html";
    }
    @RequestMapping("/user/login")
    public String login(String username,String password){
        try {
            shiroService.check(username,password);
            return "index.html";
        }catch (Exception e){
            return "login.html";
        }
    }
}
