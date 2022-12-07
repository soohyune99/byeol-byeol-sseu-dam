package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminCommunityService;
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
@RequestMapping(value = {"/admin/community/*", "/admin/community"})
public class AdminCommunityController {
    private final AdminCommunityService adminCommunityService;

    //게시글 목록
    @GetMapping("/board")
    public String adminBoard(Model model){
        model.addAttribute("boards", adminCommunityService.showBoardList());
        return "/app/admin/adminCommunityManage";
    }

    /* 게시글 삭제 */
    @PostMapping("/board/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminCommunityService.removeBoard(checkedValue);

        return new RedirectView("/admin/community/board");
    }



//  댓글 목록
    @GetMapping("/comment")
    public String adminComment(Model model){
        model.addAttribute("comments", adminCommunityService.showCommentList());
        return "/app/admin/adminComment";
    }
}
