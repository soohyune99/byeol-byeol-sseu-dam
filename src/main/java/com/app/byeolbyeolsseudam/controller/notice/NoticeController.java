package com.app.byeolbyeolsseudam.controller.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/notice/*", "/notice"})
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("")
    public String Notice(Model model){
        model.addAttribute("notices", noticeService.showListCategory());
        return "/app/notice/Notice";
    }

    @GetMapping("/{noticeId}")
    public String NoticeDetail(@PathVariable Long noticeId, Model model){
        model.addAttribute("notice", noticeService.showListDetail(noticeId));
        return "app/notice/NoticeDetail";
    }

    @GetMapping("/search")
    public String search(String keyword, Model model){
        List<NoticeDTO> searchList = noticeService.search(keyword);

        model.addAttribute("searchList", searchList);
        return "/app/notice/searchPage";
    }

}
