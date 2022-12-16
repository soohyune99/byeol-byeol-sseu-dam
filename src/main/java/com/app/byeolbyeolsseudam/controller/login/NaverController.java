package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.service.login.NaverService;
import com.app.byeolbyeolsseudam.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/naver/*")
public class NaverController {
    private final NaverService naverService;
    private final LoginService loginService;

    @ResponseBody
    @GetMapping("/login")
    public RedirectView naverCallback(@RequestParam String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        String token = naverService.getNaverAccessToken(code);
        session.setAttribute("token", token);
        MemberDTO memberDTO = naverService.naverProfile(token);
        redirectAttributes.addFlashAttribute("memberDTO",memberDTO);

        if(Optional.ofNullable(loginService.loginOauth(memberDTO)).isPresent()){
            return new RedirectView("/login/oauth");
        }else{
            return new RedirectView("/join/oauth");
        }
    }

    @GetMapping("/logout")
    public RedirectView naverLogout(HttpSession session){
        log.info("logout");

        session.invalidate();
        return new RedirectView("/main");
    }

}
