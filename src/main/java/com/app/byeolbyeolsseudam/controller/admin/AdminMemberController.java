package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminMemberService;
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
@RequestMapping(value = {"/admin/member/*", "/admin/member"})
public class AdminMemberController {

    private final AdminMemberService adminMemberService;
    /* 회원 관리 - 회원 목록  */
    @GetMapping("/{page}")
    public String adminUserManage(@PathVariable("page")Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "memberId");

        model.addAttribute("admin",adminMemberService.showAdmin());
        model.addAttribute("members",adminMemberService.showMemberList(pageable));
        return "/app/admin/adminUserManage"; }


    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminMemberService.removeMember(checkedValue);

        return new RedirectView("/admin/member/1");
    }
}
