package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.service.main.BannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/banner/*", "/admin/banner"})
@Slf4j
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("/{page}")
    public String adminBannerList(@PathVariable("page")Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,7, Sort.Direction.DESC, "bannerId");

        model.addAttribute("banners", bannerService.show(pageable));
        return "/app/admin/adminBannerList";
    }

    @PostMapping("/save")
    public RedirectView adminBannerAddedList(BannerDTO bannerDTO){
        bannerService.saveBanner(bannerDTO);
        return new RedirectView("/admin/banner/1");
    }

    /* 베너관리- 베너 추가 */
    @GetMapping("/add")
    public String adminBannerAdd(){
        return "/app/admin/adminBannerAdd";
    }

    /* 베너관리- 베너 수정 */
    @PostMapping("/modified")
    public RedirectView adminBannerModified(BannerDTO bannerDTO){
        bannerService.updateBanner(bannerDTO, bannerDTO.getBannerId());

        return new RedirectView("/admin/banner/1");
    }

    @GetMapping("/modify")
    public String adminBannerModify(@RequestParam(name = "bannerId") Long bannerId, Model model){
        model.addAttribute("oldBanner", bannerService.selectById(bannerId));

        return "/app/admin/adminBannerModify";
    }

    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        bannerService.removeBanner(checkedValue);

        return new RedirectView("/admin/banner/1");
    }
}
