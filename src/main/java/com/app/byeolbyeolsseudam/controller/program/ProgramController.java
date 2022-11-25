package com.app.byeolbyeolsseudam.controller.program;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test/*")
public class ProgramController {

    @GetMapping("/program")
    public String program(){
        return "app/Program/program";
    }

    @GetMapping("/programdetail")
    public String  programDetail(){
        return "app/Program/programDetail";
    }

}
