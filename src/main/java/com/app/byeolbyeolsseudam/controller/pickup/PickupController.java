package com.app.byeolbyeolsseudam.controller.pickup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pickup/*")
public class PickupController {

    @GetMapping("/pickmain")
    public String pickMain(){
        return "/app/pickup/pickMain";
    }
    @GetMapping("/pickdetail")
    public String pickDetail(){
        return "/app/pickup/pickDetail";
    }
    @GetMapping("/pickdetaildone")
    public String pickDetailDone(){
        return "/app/pickup/pickDetailDone";
    }
    @GetMapping("/pickwantedlist")
    public String pickWantedList(){
        return "/app/pickup/pickWantedList";
    }
    @GetMapping("/pickacceptedlist")
    public String pickAcceptedList(){
        return "/app/pickup/pickAcceptedList";
    }
    @GetMapping("/pickfinishedlist")
    public String pickFinishedList(){
        return "/app/pickup/pickFinishedList";
    }

}
