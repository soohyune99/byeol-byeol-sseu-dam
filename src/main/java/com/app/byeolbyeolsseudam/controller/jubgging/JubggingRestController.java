package com.app.byeolbyeolsseudam.controller.jubgging;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.service.jubgging.JubggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/insert/*")
//@RequestMapping(value = {"/jubgging/*, /jubgging"})
public class JubggingRestController {
    private final JubggingService jubggingService;

    /* 줍깅 QR */
    @GetMapping("/{courseName}/{spotNumber}")
    public void insertMycourse(@PathVariable String courseName, @PathVariable int spotNumber, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        jubggingService.insertMycourse(memberDTO, courseName, spotNumber);

    }
}
