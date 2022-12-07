package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/member/*", "/admin/member"})
public class AdminMemberController {

    private final AdminMemberService adminMemberService;
    /* 회원 관리 - 회원 목록  */
    @GetMapping("")
    public String adminUserManage(Model model){
        model.addAttribute("members",adminMemberService.showMemberList());
        return "/app/admin/adminUserManage"; }


    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminMemberService.removeMember(checkedValue);

        return new RedirectView("/admin/member");
    }
}
