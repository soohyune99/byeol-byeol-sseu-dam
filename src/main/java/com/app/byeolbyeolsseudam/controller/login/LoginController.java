package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.login.MemberLoginService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.transaction.Transactional;

@Controller
@RequestMapping("/login/*")
@SessionAttributes("member")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberLoginService memberLoginService;

    @GetMapping("/login")
    public String login(MemberDTO memberDTO){
        return "/app/login/login";
    }

    @PostMapping("/login")
    public RedirectView login2(MemberDTO memberDTO, Model model){

        if (memberLoginService.login(memberDTO)){
            model.addAttribute("member", memberLoginService.Get(memberDTO));

            return new RedirectView ("/main/main");
        } else {
            return new RedirectView ("/login/login");
        }

    }

    @GetMapping("/logout")
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "/app/main/logoutMain";
    }

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
