package com.app.byeolbyeolsseudam.controller.mypage;

import com.app.byeolbyeolsseudam.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/mypage/*", "/mypage"})
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("")
    public String main(){
        return "/app/mypage/mypageMain";
    }

    @GetMapping("/orderlist")
    public String orderlist(){
        return "/app/mypage/mypageOrderList";
    }

    @GetMapping("/cancellist")
    public String cancellist(){
        return "/app/mypage/mypageCancelList";
    }

    @GetMapping("/orderdetail")
    public String orderlistdetail(){
        return "/app/mypage/mypageOrderDetail";
    }

    @GetMapping("/updateinfo")
    public String myinfoupdate(){
        return "/app/mypage/mypageInfoUpdate";
    }

    @GetMapping("/myinfo")
    public String myinfo(){
        return "/app/mypage/mypageInfo";
    }

    @GetMapping("/mypoint")
    public String mypoint(Model model){
        model.addAttribute("mypoints", mypageService.selectPoints());
        return "/app/mypage/mypagePoint";
    }

    @GetMapping("/pickuplist")
    public String pickuplist(){
        return "/app/mypage/mypagePickupList";
    }

    @GetMapping("/pickupdetail")
    public String pickupdetail(){
        return "/app/mypage/mypagePickupDetail";
    }

    @GetMapping("/community")
    public String community(){
        return "/app/mypage/mypageCommunity";
    }

    @GetMapping("/comment")
    public String comment(){
        return "/app/mypage/mypageComment";
    }

    @GetMapping("/mybadge")
    public String badge(Model model){
        model.addAttribute("badges", mypageService.showBadgeList());
        model.addAttribute("mybadges", mypageService.selectMybadges());
        return "/app/mypage/mypageBadge";
    }

    @GetMapping("/mycourse")
    public String course(){
        return "/app/mypage/mypageCourse";
    }

    @GetMapping("/myprogram")
    public String program(Model model){
        return "/app/mypage/mypageProgram";
    }

}
