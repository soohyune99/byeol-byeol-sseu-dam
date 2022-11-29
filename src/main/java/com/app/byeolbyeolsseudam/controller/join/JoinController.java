package com.app.byeolbyeolsseudam.controller.join;

import com.app.byeolbyeolsseudam.domain.MemberDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join/*")
public class JoinController {

    @GetMapping("/join")
    public String join(MemberDTO memberDTO){

        return "/app/join/joinNormal";
    }

    @GetMapping("/picker")
    public String pickIntro(){
        return "/app/join/pickIntro";
    }
    @GetMapping("/picker/adr")
    public String joinpickerOne(){
        return "/app/join/joinCollectorStepOne";
    }
    @GetMapping("/picker/map")
    public String joinpickerOneMap(){
        return "/app/join/joinCollectorStepOneMap";
    }
    @GetMapping("/picker/detail")
    public String joinpickerTwo(){
        return "/app/join/joinCollectorStepTwo";
    }

}
