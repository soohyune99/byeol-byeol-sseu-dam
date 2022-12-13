package com.app.byeolbyeolsseudam.controller.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.join.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/join/*", "/join"})
public class JoinController {
    private final JoinService joinService;

    /* 회원가입 페이지로 이동 */
    @GetMapping("")
    public String join(MemberDTO memberDTO){
        return "/app/join/joinNormal";
    }

    /* 회원가입 완료 시 */
    @PostMapping("")
    public String memberJoin(MemberDTO memberDTO){
        joinService.memberJoin(memberDTO);
        return "redirect:/main?join=true";
    }

    /* 기사 회원가입 안내 페이지로 이동 */
    @GetMapping("/picker")
    public String pickIntro(){
        return "/app/join/pickIntro";
    }

    /* 기사 회원가입 ㅡ 주소입력 */
    @GetMapping("/picker/map")
    public String joinpickerOneMap(MemberDTO memberDTO){
        return "/app/join/joinCollectorStepOneMap";
    }

    /* 기사 회원가입 ㅡ 상세정보 입력 */
    @GetMapping("/picker/detail")
    public String joinpickerTwo(MemberDTO memberDTO){
        return "/app/join/joinCollectorStepTwo";
    }

    /* 기사 회원가입 완료 시 */
    @PostMapping("/picker/detail")
    public RedirectView crewJoin(MemberDTO memberDTO){
        joinService.crewJoin(memberDTO);
        return new RedirectView ("/main?join=true");
    }

    /* 이메일 중복확인 */
    @ResponseBody
    @GetMapping("checkEmail")
    public boolean checkEmail(String memberEmail){
        return joinService.checkEmail(memberEmail);
    }

//    @PostMapping("/kakao")
//    public void joinKakao(String kakaoId){;}


}
