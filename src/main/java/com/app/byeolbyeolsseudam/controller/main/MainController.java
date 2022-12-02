package com.app.byeolbyeolsseudam.controller.main;

import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/main/*")
public class MainController {
    private final BannerService bannerService;


//    @GetMapping("/main")
//    public String main(){
//        return "/app/main/main";
//    }

    @GetMapping("/main")
    public String read(Model model){
        model.addAttribute("banners", bannerService.show());
        return "/app/main/main";
    }

}
