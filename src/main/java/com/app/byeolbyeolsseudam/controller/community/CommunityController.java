package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.service.community.CommunityService;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/community/*", "/community"})
public class CommunityController {
    private final CommunityService communityService;

    @GetMapping("")
    public String main(Model model){
        model.addAttribute("topViews", communityService.selectTopView());
        model.addAttribute("boards", communityService.selectBoards());
        return "/app/community/communityMain";
    }

    @PostMapping("/{boardCategory}")
    public String mainCategory(@PathVariable BoardCategory boardCategory, Model model){
        model.addAttribute("topViews", communityService.selectTopView());
        model.addAttribute("boards", communityService.selectBoardsofCategory(boardCategory));
        return "/app/community/communityMain";
    }

    @GetMapping("/{boardId}")
    public String Read(@PathVariable Long boardId, Model model){
        BoardDTO boardDTO = communityService.readBoard(boardId);
        model.addAttribute("board", boardDTO);
        return "/app/community/communityRead";
    }

    @GetMapping("/search/{keyword}")
    public String search(@PathVariable String keyword, Model model){
        model.addAttribute("boards", communityService.selectBoardsofKeyword(keyword));
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
