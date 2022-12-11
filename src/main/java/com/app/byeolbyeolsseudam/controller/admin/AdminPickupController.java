package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminPickService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/pickup/*","/admin/pickup"})
public class AdminPickupController {
    private final AdminPickService adminPickService;
    /* 수거 서비스 - 수거 목록*/
    @GetMapping("/{page}")
    public String adminPickup(@PathVariable("page")Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "pickupId");

        model.addAttribute("pickups", adminPickService.showList(pageable));
        return "/app/admin/adminCollectService";
    }

    /* 수거 서비스 - 수거 목록 + 상세 내역 */
    @GetMapping("detail")
    public String adminPickupDetail(@RequestParam(name = "pickupId") String pickupId, Model model){
        model.addAttribute("pickupDetail", adminPickService.selectById(pickupId));
        return "/app/admin/adminCollectServiceDetail";
    }




}
