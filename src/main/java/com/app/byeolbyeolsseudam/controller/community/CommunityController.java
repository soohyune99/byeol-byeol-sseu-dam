package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/{boardId}")
    public String Read(@PathVariable Long boardId, Model model){
        model.addAttribute("board", communityService.readBoard(boardId));
        return "/app/community/communityRead";
    }

    @GetMapping("/search")
    public String search(){
        return "/app/community/communitysearch";
    }

    @GetMapping("/write")
    public String write(){
        return "/app/community/communityWrite";
    }


}
