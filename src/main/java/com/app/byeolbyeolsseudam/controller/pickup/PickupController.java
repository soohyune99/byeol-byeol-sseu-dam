package com.app.byeolbyeolsseudam.controller.pickup;

import com.app.byeolbyeolsseudam.service.pickup.PickupService2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pickup/*")
@RequiredArgsConstructor
public class PickupController {

    private final PickupService2 pickupService2;

    @GetMapping("/main")
    public String pickMain(){
        return "/app/pickup/pickMain";
    }

    @GetMapping("/detaildone")
    public String pickDetailDone(){
        return "/app/pickup/pickDetailDone";
    }

    @GetMapping("/wantedlist")
    public String pickWantedList(Model model){
        model.addAttribute("pickups", pickupService2.getPickupList());

        return "/app/pickup/pickWantedList";
    }

    @GetMapping("/detail")
    public String pickDetail(Long pickId, Model model){

        return "/app/pickup/pickDetail";
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
