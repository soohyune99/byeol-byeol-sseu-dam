package com.app.byeolbyeolsseudam.controller.recycling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test/*")
public class RecyclingController {

    @GetMapping("/recycling")
    public String recycling(){
        return "app/recycling/recycling";
    }


    @GetMapping("/recyclingintro")
    public  String recyclingIntro(){
        return "app/recycling/recyclingIntro";
    }

    @GetMapping("/question")
    public String question(){return "app/question/QuestionIntro";}
}
