package com.app.byeolbyeolsseudam.controller.jubgging;

import com.app.byeolbyeolsseudam.service.jubgging.JubggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/jubgging/*, /jubgging"})
public class JubggingRestController {
    private final JubggingService jubggingService;

    /* 줍깅 QR */
    @PostMapping("/{memberId}/{courseName}/{spotNumber}")
    public void insertMycourse(@PathVariable Long memberId, @PathVariable String courseName, @PathVariable int spotNumber){
        log.info("들어옴//////////////////////////////////////////////////////////");
        log.info("===================================================" + memberId);
        log.info("===================================================" + courseName);
        log.info("===================================================" + spotNumber);
        jubggingService.insertMycourse(memberId, courseName, spotNumber);

//        return myCourseDTO;
    }
}
