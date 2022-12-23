package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.login.LoginService;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/login/*", "/login"})
public class LoginController {
    private final LoginService loginService;

    /* 로그인 페이지로 이동 */
    @GetMapping("")
    public String login(MemberDTO memberDTO){
        return "/app/login/login";
    }

    /* 로그인 */
    /* Servlet HTTP 세션 사용 */
    @PostMapping("")
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

        return "redirect:/main";
    }

    /* Oauth 로그인 */
    /* Servlet HTTP 세션 사용 */
    @GetMapping("/oauth")
    public String oauthLogin(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            log.info("실패");
            return "/main";
        }
        Member loginMember = loginService.loginOauth(memberDTO);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "redirect:/login?login=fail";

        } else if(loginMember.getMemberCategory() == MemberCategory.탈퇴회원) {
            return "redirect:/login?login=fail";
        }

        session.setAttribute("member", loginService.getMemberDTO(loginMember.getMemberId()));

        return "redirect:/main";
    }

    /* 비밀번호 찾기 페이지로 이동 */
    @GetMapping("/pw")
    public String findPassword(MemberDTO memberDTO){
        return "/app/login/findPassword";
    }

    /* 임시 비밀번호 전송 시 */
    @PostMapping("/pw")
    public RedirectView send(MemberDTO memberDTO, RedirectAttributes redirectAttributes){
        if(!loginService.send(memberDTO)){
            return new RedirectView("/login/pw?pw=false");
        }
        redirectAttributes.addFlashAttribute("memberEmail", memberDTO.getMemberEmail());
        return new RedirectView("/login/pw/done");
    }

    /* 임시 비밀번호 전송 완료 페이지로 이동 */
    @GetMapping("/pw/done")
    public String findPasswordDone(){
        return "/app/login/findPasswordDone";
    }

}
