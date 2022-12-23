package com.app.byeolbyeolsseudam.controller.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/notice/*", "/notice"})
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    /* 공지사항 일반 조회 / 공지사항 중요 + 일반 조회*/
    @GetMapping("/{page}" )
    public String notice(@PathVariable("page") Integer page, Model model){
        Pageable pageable = PageRequest.of((page - 1),10, Sort.Direction.DESC, "createdDate");

        model.addAttribute("paging", noticeService.showListCategory(pageable));
        model.addAttribute("notices", noticeService.showListAll(pageable));
        return "app/notice/Notice";
    }

    /* 공지사항 상세 조회 */
    @GetMapping("detail/{noticeId}")
    public String noticeDetail(@PathVariable Long noticeId, Model model){
        model.addAttribute("notice", noticeService.showListDetail(noticeId));
        return "app/notice/NoticeDetail";
    }

    /* 공지사항 검색 */
    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page,5, Sort.Direction.DESC, "createdDate");

        model.addAttribute("keyword", keyword);
        model.addAttribute("searchResult", noticeService.search(keyword, pageable));
        model.addAttribute("searchCount", noticeService.search(keyword, pageable).getTotalElements());
        return "app/notice/NoticeSearch";
    }
}
