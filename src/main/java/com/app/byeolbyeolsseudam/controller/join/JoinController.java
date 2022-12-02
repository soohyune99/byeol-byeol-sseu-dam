package com.app.byeolbyeolsseudam.controller.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.service.join.MemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join/*")
public class JoinController {

    private final MemberJoinService memberJoinService;

    @GetMapping("/join")
    public String join(MemberDTO memberDTO){

        return "/app/join/joinNormal";
    }

    @PostMapping("/join")
    public RedirectView memberJoin(MemberDTO memberDTO){

        memberJoinService.Join(memberDTO);

        return new RedirectView ("/main/main");
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
