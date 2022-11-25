package com.app.byeolbyeolsseudam.controller.jubgging;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/jubgging/*")
public class JubggingController {

    @GetMapping("/jubgging-intro")
    public String programIntro(){
        return "/app/jubgging/jubggingIntro";
    }

    @GetMapping("/jubgging")
    public String program(){
        return "/app/jubgging/jubgging";
    }

    @GetMapping("/jubgging-qr")
    public String programQr(){
        return "/app/jubgging/jubggingqr";
    }
}
