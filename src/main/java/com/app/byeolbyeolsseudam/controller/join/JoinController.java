package com.app.byeolbyeolsseudam.controller.join;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {

    @GetMapping("/join")
    public String join(){
        return "/app/join/joinNormal";
    }

    @GetMapping("/pickintro")
    public String pickIntro(){
        return "/app/join/pickIntro";
    }
    @GetMapping("/joinpicker1")
    public String joinpickerOne(){
        return "/app/join/joinCollectorStepOne";
    }
    @GetMapping("/joinpicker1map")
    public String joinpickerOneMap(){
        return "/app/join/joinCollectorStepOneMap";
    }
    @GetMapping("/joinpicker2")
    public String joinpickerTwo(){
        return "/app/join/joinCollectorStepTwo";
    }

}
