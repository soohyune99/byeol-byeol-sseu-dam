package com.app.byeolbyeolsseudam.controller.community;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community/*")
public class CommunityController {

    @GetMapping("/main")
    public String main(){
        return "/app/community/communityMain";
    }

    @GetMapping("/read")
    public String Read(){
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
