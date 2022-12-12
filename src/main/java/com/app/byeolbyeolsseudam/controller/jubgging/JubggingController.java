package com.app.byeolbyeolsseudam.controller.jubgging;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import com.app.byeolbyeolsseudam.service.jubgging.JubggingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/jubgging/*", "/jubgging"})
public class JubggingController {
    private final JubggingService jubggingService;

    @GetMapping("")
    public String jubggingIntro(){
        return "/app/jubgging/jubggingIntro";
    }

    /* 코스 번호 받아서 코스 조회 */
    @GetMapping("/{cno}")
    public String jubggingCourse(@PathVariable("cno")int courseNumber, Model model){
        CourseDTO courseDTO = jubggingService.showCourse(courseNumber);
        model.addAttribute("course", courseDTO);
//        model.addAttribute("spots", courseDTO.getSpots());
        return "/app/jubgging/jubgging";
    }

    /* 스페셜 코스 조회 */
    @GetMapping("/special")
    public String jubggingSpecial(Model model){
        model.addAttribute("course", jubggingService.showSpecialCourse());
        return "/app/jubgging/jubgging";
    }

    @GetMapping("/{courseName}/{spotNumber}")
    public String jubggingQr(@PathVariable String courseName, @PathVariable int spotNumber){
        return "/app/login/login";
    }
}
