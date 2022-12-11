package com.app.byeolbyeolsseudam.controller.mypage;

import com.app.byeolbyeolsseudam.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/order")
    public String orderlist(){
        return "/app/mypage/mypageOrderList";
    }

    @GetMapping("/cancel")
    public String cancellist(){
        return "/app/mypage/mypageCancelList";
    }

    @GetMapping("/order/{orderId}")
    public String orderlistdetail(){
        return "/app/mypage/mypageOrderDetail";
    }

    @GetMapping("/info/update")
    public String myinfoupdate(){
        return "/app/mypage/mypageInfoUpdate";
    }

    @GetMapping("/info/password")
    public String myinfopassword(){
        return "/app/mypage/mypageInfopassword";
    }

    @GetMapping("/info")
    public String myinfo(){
        return "/app/mypage/mypageInfo";
    }

    @GetMapping("/point")
    public String mypoint(){
        return "/app/mypage/mypagePoint";
    }

    @GetMapping("/pickup")
    public String pickuplist(){
        return "/app/mypage/mypagePickupList";
    }

    @GetMapping("/pickup/{pickupId}")
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

    @GetMapping("/badge")
    public String badge(){
        return "/app/mypage/mypageBadge";
    }

    @GetMapping("/course")
    public String course(){
        return "/app/mypage/mypageCourse";
    }

    @GetMapping("/program")
    public String program(){
        return "/app/mypage/mypageProgram";
    }

}
