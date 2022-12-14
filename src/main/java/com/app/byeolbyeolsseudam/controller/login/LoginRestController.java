package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.login.LoginService;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/login", "/login/*"})
public class LoginRestController {
    private final LoginService loginService;

    @PostMapping("/rest")
    public String login(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            log.info("실패");
            return "/main";
        }
        Member loginMember = loginService.login(memberDTO);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/login?login=fail";

        } else if(loginMember.getMemberCategory() == MemberCategory.탈퇴회원) {
            return "redirect:/login?login=fail";
        }

        session.setAttribute("member", loginService.getMemberDTO(loginMember.getMemberId()));

        return "redirect:/login?memberId=" + loginMember.getMemberId();
    }
}
