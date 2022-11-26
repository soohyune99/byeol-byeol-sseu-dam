package com.app.byeolbyeolsseudam.controller.pickup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pickup/*")
public class PickupController {

    @GetMapping("/main")
    public String pickMain(){
        return "/app/pickup/pickMain";
    }
    @GetMapping("/detail")
    public String pickDetail(){
        return "/app/pickup/pickDetail";
    }
    @GetMapping("/detaildone")
    public String pickDetailDone(){
        return "/app/pickup/pickDetailDone";
    }
    @GetMapping("/wantedlist")
    public String pickWantedList(){
        return "/app/pickup/pickWantedList";
    }
    @GetMapping("/acceptedlist")
    public String pickAcceptedList(){
        return "/app/pickup/pickAcceptedList";
    }
    @GetMapping("/okdetail")
    public String pickOkDetail(){
        return "/app/pickup/pickOkDetail";
    }
    @GetMapping("/finishedlist")
    public String pickFinishedList(){
        return "/app/pickup/pickFinishedList";
    }
    @GetMapping("/finisheddetail")
    public String pickFinishedDetail(){
        return "/app/pickup/pickFinishedDetail";
    }

}
