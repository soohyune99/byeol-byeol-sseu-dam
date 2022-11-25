package com.app.byeolbyeolsseudam.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login/*")
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "/app/login/login";
    }

    @GetMapping("/findpassword")
    public String findPassword(){
        return "/app/login/findPassword";
    }

    @GetMapping("/findpassworddone")
    public String findPasswordDone(){
        return "/app/login/findPasswordDone";
    }
}
