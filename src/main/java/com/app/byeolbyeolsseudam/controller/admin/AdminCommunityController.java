package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.service.admin.AdminCommunityService;
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
@RequestMapping(value = {"/admin/community/*", "/admin/community"})
public class AdminCommunityController {
    private final AdminCommunityService adminCommunityService;

    //게시글 목록
    @GetMapping("/board/{page}")
    public String adminBoard(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "boardId");

        model.addAttribute("boards", adminCommunityService.searchBoard(pageable));
        return "/app/admin/adminCommunityManage";
    }

    @GetMapping("/board")
    public String adminBoardSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "boardId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("boards",adminCommunityService.searchBoardPaging(keyword, pageable));
        model.addAttribute("boardsCount",adminCommunityService.searchBoardPaging(keyword, pageable).getTotalElements());

        return "/app/admin/adminCommunityManage";
    }

    /* 게시글 삭제 */
    @PostMapping("/board/delete")
    public RedirectView adminBoardDelete(@RequestParam List<String> checkedValue){
        adminCommunityService.removeBoard(checkedValue);

        return new RedirectView("/admin/community/board/1");
    }


//  댓글 목록
    @GetMapping("/comment/{page}")
    public String adminComment(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "commentId");

        model.addAttribute("comments", adminCommunityService.searchComment(pageable));
        return "/app/admin/adminComment";
    }

    @GetMapping("/comment")
    public String adminCommentSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "commentId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("comments",adminCommunityService.searchCommentPaging(keyword, pageable));
        model.addAttribute("commentsCount",adminCommunityService.searchCommentPaging(keyword, pageable).getTotalElements());

        return "/app/admin/adminComment";
    }


    @PostMapping("/comment/delete")
    public RedirectView adminCommentDelete(@RequestParam List<String> checkedValue){
        adminCommunityService.removeComment(checkedValue);

        return new RedirectView("/admin/community/comment/1");
    }
}
