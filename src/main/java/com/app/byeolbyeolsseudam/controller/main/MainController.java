package com.app.byeolbyeolsseudam.controller.main;

import com.app.byeolbyeolsseudam.service.main.BannerService;
import com.app.byeolbyeolsseudam.service.main.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/main/*","/main"})
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
        return "/app/main/main";
    }
}
