package com.app.byeolbyeolsseudam.controller.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.join.JoinService;
import com.app.byeolbyeolsseudam.service.join.MemberJoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/join/*")
@Slf4j
public class JoinController {

    private final JoinService joinService;

//    @SessionAttribute("member") Member member
//        log.info(member.getMemberEmail());


    //  가입 싸이트 접근
    @GetMapping("/join")
    public String join(MemberDTO memberDTO){
        return "/app/join/joinNormal";
    }

    //  가입
    @PostMapping("/join")
    public RedirectView memberJoin(MemberDTO memberDTO){

        joinService.memberJoin(memberDTO);

        return new RedirectView ("/main/main");
    }

    //  기사 가입
    @GetMapping("/picker")
    public String pickIntro(){
        return "/app/join/pickIntro";
    }

    @GetMapping("/picker/adr")
    public String joinpickerOne(){
        return "/app/join/joinCollectorStepOne";
    }

    //
    @GetMapping("/picker/map")
    public String joinpickerOneMap(MemberDTO memberDTO){

//        redirectAttributes.addAttribute("memberAddress", memberDTO.getMemberAddress());

        return "/app/join/joinCollectorStepOneMap";
    }



    @GetMapping("/picker/detail")
    public String joinpickerTwo(MemberDTO memberDTO){

        return "/app/join/joinCollectorStepTwo";
    }

    @PostMapping("/picker/detail")
    public RedirectView crewJoin(MemberDTO memberDTO){
        joinService.crewJoin(memberDTO);
        return new RedirectView ("/main/main");
    }

    //   중복확인
    @ResponseBody
    @GetMapping("checkEmail")
    public boolean checkEmail(String memberEmail){
        return joinService.checkEmail(memberEmail);
    }

}
