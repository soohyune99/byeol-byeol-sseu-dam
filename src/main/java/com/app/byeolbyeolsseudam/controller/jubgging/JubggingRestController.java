package com.app.byeolbyeolsseudam.controller.jubgging;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.service.jubgging.JubggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/course/*")
public class JubggingRestController {
    private final JubggingService jubggingService;

    /* 줍깅 QR */
    @GetMapping("/{courseName}/{spotNumber}")
    public MycourseDTO insertMycourse(@PathVariable String courseName, @PathVariable int spotNumber, HttpSession session) {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        return jubggingService.insertMycourse(memberDTO, courseName, spotNumber);
    }
}
