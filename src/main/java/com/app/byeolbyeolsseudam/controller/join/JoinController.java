package com.app.byeolbyeolsseudam.controller.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.join.JoinService;
import com.app.byeolbyeolsseudam.service.join.MemberJoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join/*")
@Slf4j
public class JoinController {

    private final JoinService joinService;

//    @SessionAttribute("member") Member member
//        log.info(member.getMemberEmail());

    @GetMapping("/join")
    public String join(MemberDTO memberDTO){


        return "/app/join/joinNormal";
    }

    @PostMapping("/join")
    public RedirectView memberJoin(MemberDTO memberDTO, RedirectView redirectView){

        joinService.memberJoin(memberDTO);

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
