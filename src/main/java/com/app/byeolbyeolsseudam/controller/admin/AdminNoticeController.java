package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.service.admin.AdminNoticeService;
import com.app.byeolbyeolsseudam.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/notice/*", "/admin/notice"})
public class AdminNoticeController {
    private final AdminNoticeService adminNoticeService;

    /* 고객센터 - 공지사항 목록 */
    @GetMapping("/{page}")
    public String adminNotice(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "noticeId");


        model.addAttribute("adminNotices", adminNoticeService.showList(pageable));
        return "/app/admin/adminNotice";
    }
    @GetMapping("/search")
    public String adminNotice(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "noticeId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("adminNotices", adminNoticeService.searchNotice(keyword, pageable));
        model.addAttribute("adminNoticesCount", adminNoticeService.searchNotice(keyword, pageable).getTotalElements());
        return "/app/admin/adminNotice";
    }



    /* 고객센터 - 공지사항 목록 + 공지사항 추가 */

    @GetMapping("/add")
    public String adminNoticeAdd(){
        return "/app/admin/adminNoticeAdd";
    }

    @PostMapping("/save")
    public RedirectView adminNoticeSave(NoticeDTO noticeDTO){
        adminNoticeService.saveNotice(noticeDTO);
        return new RedirectView("/admin/notice/1");
    }

    /* 고객센터 - 공지사항 목록 + 공지사항 수정 */
    @GetMapping("/modify")
    public String adminNoticeModify(@RequestParam(name = "noticeId") Long noticeId, Model model){
        model.addAttribute("notice", adminNoticeService.selectById(noticeId));
        return "/app/admin/adminNoticeModify";
    }

    @PostMapping("/modified")
    public RedirectView adminNoticeModified(NoticeDTO noticeDTO){
        adminNoticeService.updateNotice(noticeDTO, noticeDTO.getNoticeId());
        return new RedirectView("/admin/notice/1");
    }

    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminNoticeService.removeNotice(checkedValue);
        return new RedirectView("/admin/notice/1");
    }
}
