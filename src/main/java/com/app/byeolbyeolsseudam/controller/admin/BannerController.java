package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/banner/*", "/admin/banner"})
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("")
    public String adminBannerList(Model model){
        model.addAttribute("banners", bannerService.show());
        return "/app/admin/adminBannerList";
    }

    @PostMapping("/save")
    public RedirectView adminBannerAddedList(BannerDTO bannerDTO){
        bannerService.saveBanner(bannerDTO);
        return new RedirectView("/admin/banner");
    }

    /* 베너관리- 베너 추가 */
    @GetMapping("/add")
    public String adminBannerAdd(){
        return "/app/admin/adminBannerAdd";
    }

    /* 베너관리- 베너 수정 */
    @GetMapping("/modify")
    public String adminBannerModify(){
        return "/app/admin/adminBannerModify";
    }


}
