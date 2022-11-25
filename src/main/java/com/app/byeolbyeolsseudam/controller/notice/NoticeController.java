package com.app.byeolbyeolsseudam.controller.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test/*")
public class NoticeController {

    @GetMapping("/notice")
    public String Notice(){
        return "app/Notice/Notice";
    }

    @GetMapping("/noticedetail")
    public String NoticeDetail(){
        return "app/Notice/NoticeDetail";
    }

}
