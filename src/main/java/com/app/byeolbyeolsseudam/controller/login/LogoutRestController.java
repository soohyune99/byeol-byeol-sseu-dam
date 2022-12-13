package com.app.byeolbyeolsseudam.controller.login;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/logout/*", "/logout"})
public class LogoutRestController {

    @GetMapping("/dropout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/main";
    }
}
