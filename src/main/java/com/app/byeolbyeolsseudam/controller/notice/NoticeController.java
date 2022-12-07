package com.app.byeolbyeolsseudam.controller.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/notice/*", "/notice"})
public class NoticeController {

    private final NoticeService noticeService;

    /* 공지사항 중요 + 일반 조회 */
/*    @GetMapping("")
    public String notice(Model model){
//        Pageable pageable = PageRequest.of(page,10, Sort.Direction.DESC);

        model.addAttribute("notices", noticeService.showListCategory());
//        model.addAttribute("notices", noticeService.showListAll(pageable));
        return "app/notice/Notice";
    }*/

    /* 공지사항 일반 조회 */
//    @GetMapping(value={"","/notice/{page}"})
//    @GetMapping("")
    @GetMapping("notice/{page}")
//    public String notice(Model model, @PathVariable("page") Integer page){
//    public String notice(@PageableDefault int page, Model model){
    public String notice(Model model, @RequestParam(value="page", defaultValue="0") Integer page){
        Pageable pageable = PageRequest.of(page,10, Sort.Direction.DESC);

        model.addAttribute("paging", noticeService.showListAll(pageable));
//        log.info("hasNext: " + superCars.hasNext());
//        log.info("hasPrevious: " +superCars.hasPrevious());
//        model.addAttribute("nowPage", noticeService.showListAll(pageable).getNumber());     // 현재 페이지 번호
//        model.addAttribute("allPage", noticeService.showListAll(pageable).getTotalPages()); //전체 페이지 번호
//        model.addAttribute("firstPage", noticeService.showListAll(pageable).isFirst());     //첫 페이지인지 확인
//        model.addAttribute("nextPage", noticeService.showListAll(pageable).isLast());       //다음 페이지가 있는지 확인
//        model.addAttribute("isprevPage", noticeService.showListAll(pageable).hasPrevious()); //이전 페이지가 있는지 확인
//        model.addAttribute("isnextPage", noticeService.showListAll(pageable).hasNext());    //다음 페이지가 있는지 확인

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
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        model.addAttribute("notices", noticeService.search(keyword));
        return "app/notice/Notice";
    }

}
