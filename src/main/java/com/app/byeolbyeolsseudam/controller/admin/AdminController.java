/*
    author: JungJaehun
    contents: 관리자 페이지
*/
package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminMemberService;
import com.app.byeolbyeolsseudam.service.admin.AdminPickService;
import com.app.byeolbyeolsseudam.service.admin.AdminProductService;
import com.app.byeolbyeolsseudam.service.admin.AdminProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/admin","/admin"})
public class AdminController {
    private final AdminMemberService adminMemberService;
    private final AdminProgramService adminProgramService;
    private final AdminPickService adminPickService;
    private final AdminProductService adminProductService;

    /* 사이드바 */
    @GetMapping("adminsidebar")
    public String adminSideBar(){ return "/app/admin/adminSideBar"; }

    /* 대시보드 */
    @GetMapping("")
    public String adminMain(Model model){
        model.addAttribute("members", adminMemberService.showAdminMemberList());
        model.addAttribute("programs", adminProgramService.showList());
        model.addAttribute("pickups", adminPickService.showAdminPickupList());
        model.addAttribute("orders", adminProductService.showAdminOrderList());

        return "/app/admin/adminMain"; }

}