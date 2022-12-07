package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/banner/*", "/admin/banner"})
@Slf4j
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
    @PostMapping("/modified")
    public RedirectView adminBannerModify(BannerDTO bannerDTO){
        log.info("controller" + bannerDTO);
        bannerService.updateBanner(bannerDTO, bannerDTO.getBannerId());

        return new RedirectView("/admin/banner");
    }

    @GetMapping("/modify")
    public String adminBannerModified(@RequestParam(name = "bannerId") Long bannerId, Model model ){
        model.addAttribute("oldBanner", bannerService.selectById(bannerId));

        return "/app/admin/adminBannerModify";
    }

    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        bannerService.removeBanner(checkedValue);

        return new RedirectView("/admin/banner");
    }
}
