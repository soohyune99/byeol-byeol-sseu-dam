package com.app.byeolbyeolsseudam.controller.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.service.login.GoogleJoinService;
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
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/google/*")
public class GoogleController {
    private final GoogleJoinService googleJoinService;
    private final LoginService loginService;

    @GetMapping("/login")
    public RedirectView googleLogin(@RequestParam String code, HttpSession session, RedirectAttributes redirectAttributes) throws Exception {
        MemberDTO memberDTO = googleJoinService.loginInfo(code);
        redirectAttributes.addFlashAttribute("memberDTO", memberDTO);

        if(Optional.ofNullable(loginService.loginOauth(memberDTO)).isPresent()){
            return new RedirectView("/login/oauth");
        }else {
            return new RedirectView("/join/oauth");
        }
    }

}
