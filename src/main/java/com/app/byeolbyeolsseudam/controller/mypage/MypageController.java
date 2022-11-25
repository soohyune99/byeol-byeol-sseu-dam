package com.app.byeolbyeolsseudam.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

    @GetMapping("/main")
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
    public String mypoint(){
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
