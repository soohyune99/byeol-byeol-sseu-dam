package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.service.login.KakaoService;
import com.app.byeolbyeolsseudam.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/kakao/*")
public class KakaoController {
    private final KakaoService kakaoService;
    private final LoginService loginService;

    @ResponseBody
    @GetMapping("/login")
    public RedirectView  kakaoCallback(@RequestParam String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        log.info(code);
        String token = kakaoService.getKaKaoAccessToken(code);
        session.setAttribute("token", token);
        MemberDTO memberDTO = kakaoService.getKakaoInfo(token);
        redirectAttributes.addFlashAttribute("memberDTO", memberDTO);

        if(Optional.ofNullable(loginService.loginOauth(memberDTO)).isPresent()){
            return new RedirectView("/login/oauth");
        }else {
            return new RedirectView("/join/oauth");
        }
    }

    @GetMapping("/logout")
    public RedirectView kakaoLogout(HttpSession session){
        log.info("logout");
        kakaoService.logoutKakao((String)session.getAttribute("token"));
        session.invalidate();
        return new RedirectView("/main");
    }
}
