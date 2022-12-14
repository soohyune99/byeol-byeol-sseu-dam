package com.app.byeolbyeolsseudam.controller.main;

import com.app.byeolbyeolsseudam.controller.login.SessionConst;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import com.app.byeolbyeolsseudam.service.main.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {
    private final BannerService bannerService;
    private final MainService mainService;

    @GetMapping("")
    public String read(Model model){
        model.addAttribute("banners", bannerService.showMainBanner());
        model.addAttribute("programs", mainService.showProgram());
        model.addAttribute("kitchens", mainService.showProductKitchen());
        model.addAttribute("bathes", mainService.showProductBath());
        model.addAttribute("lives", mainService.showProductLife());
        model.addAttribute("topBoards", mainService.showTopViewBoardList());
        model.addAttribute("boards", mainService.showBoardList());
        model.addAttribute("courses", mainService.showCourseList());
        return "/app/main/main";
    }

    @PostMapping("/login")
    public String mainLogin(HttpServletRequest request, Model model){
        // getSession(true)를 사용하면 처음 들어온 사용자도 세션이 만들어지기 때문에 false로 받음
        HttpSession session = request.getSession(false);
        if(session == null){
            return "/main";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 main
        if(loginMember == null){
            return "/main";
        }
        // 세션이 유지되면 로그인으로 이동
        model.addAttribute("memberDTO", loginMember);
        return "/login";
    }
}
