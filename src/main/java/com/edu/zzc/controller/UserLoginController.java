package com.edu.zzc.controller;

import com.edu.zzc.service.UserService;
import com.edu.zzc.util.NotResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserLoginController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/login.do")
    public NotResult execute(String name, String password){
        return userService.checkLogin(name,password);
    }
}
