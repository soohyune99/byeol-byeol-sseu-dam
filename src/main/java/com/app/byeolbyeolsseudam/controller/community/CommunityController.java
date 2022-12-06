package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.service.community.CommunityService;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/community/*", "/community"})
public class CommunityController {
    private final CommunityService communityService;

    /* 커뮤니티 페이지 이동 */
    @GetMapping("")
    public String main(){
        return "/app/community/communityMain";
    }

    /* 카테고리별 목록 조회 */
    @PostMapping("/{boardCategory}")
    public String mainCategory(@PathVariable BoardCategory boardCategory, Model model){
        model.addAttribute("topViews", communityService.selectTopView());
        model.addAttribute("boards", communityService.selectBoardsofCategory(boardCategory));
        return "/app/community/communityMain";
    }

    /* 게시글 상세보기 */
    @GetMapping("/{boardId}")
    public String Read(@PathVariable Long boardId, Model model){
        BoardDTO boardDTO = communityService.readBoard(boardId);
        model.addAttribute("board", boardDTO);
        return "/app/community/communityRead";
    }

    /* 키워드 검색 */
    @GetMapping("/search/{keyword}")
    public String search(@PathVariable String keyword, Model model){
        model.addAttribute("boards", communityService.selectBoardsofKeyword(keyword));
        return "/app/community/communityMain";
    }

    @GetMapping("/communitysearch")
    public String searchPage(){
        return "/app/community/communitySearch";
    }

    @GetMapping("/write")
    public String write(){
        return "/app/community/communityWrite";
    }

    @PostMapping("/write")
    public RedirectView writeSubmit(BoardDTO boardDTO){
        communityService.saveBoard(boardDTO);
        return new RedirectView("/community");
    }


}
