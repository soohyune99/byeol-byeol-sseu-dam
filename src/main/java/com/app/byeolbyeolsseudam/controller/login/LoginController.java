package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.login.LoginService;
import com.app.byeolbyeolsseudam.service.login.MemberLoginService;
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
@RequestMapping("/login/*")
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final LoginService loginService;

    private final MemberLoginService memberLoginService;

    @GetMapping("/login")
    public String login(MemberDTO memberDTO){
        return "/app/login/login";
    }

    // Servlet HTTP 세션 사용
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("memberDTO") MemberDTO memberDTO, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()){
            log.info("실패");
            return "/login/login";
        }
        Member loginMember = loginService.login(memberDTO);

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login";
        }
        session.setAttribute("member", loginService.getMemberDTO(loginMember.getMemberId()));

        return "redirect:/main";
    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/main";
    }

    /* =========================================================================== */


    @GetMapping("/findpassword")
    public String findPassword(MemberDTO memberDTO){
        return "/app/login/findPassword";
    }

//    @Transactional
//    @PostMapping("/findpassword")
//    public String sendEmail(@RequestParam("memberEmail") String memberEmail, MemberDTO memberDTO){
//        MailDTO mailDTO = memberLoginService.createMailAndChangePassword(memberDTO);
//        memberLoginService.mailSend(mailDTO);
//
//        return "/app/login/findPasswordDone";
//    }

    @PostMapping("/findpassword")
    public RedirectView send(MemberDTO memberDTO, RedirectAttributes redirectAttributes){

        memberLoginService.Send(memberDTO);

        redirectAttributes.addFlashAttribute("memberEmail", memberDTO.getMemberEmail());

        return new RedirectView("/login/findpassworddone");
    }

    @RequestMapping(value = "/findpassworddone", method = RequestMethod.GET)
    public String findPasswordDone(){

        return "/app/login/findPasswordDone";
    }
}
