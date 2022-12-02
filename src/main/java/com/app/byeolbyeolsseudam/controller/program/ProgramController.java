package com.app.byeolbyeolsseudam.controller.program;

import com.app.byeolbyeolsseudam.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/program/*")
public class ProgramController {
    private final ProgramService programService;

    /* 프로그램 기본 List */
    @GetMapping("/list")
    public String program(Model model){
        model.addAttribute("programs", programService.programAllList());
        return "/app/program/program";
    }

    /* 프로그램 Search List _ Keyword 검색 */
    @GetMapping("/list/search")
    public String programSearchKeyword(String keyword, Model model){
        model.addAttribute("programs", programService.searchProgram(keyword));
        return "/app/program/program";
    }


    /* ####################################################################################################### */


    /* 프로그램 DETAIL 창 */
    @GetMapping("/programdetail")
    public String programDetail() {
        return "/app/program/programDetail";
    }


    /* ####################################################################################################### */

//    /* 전체 program List _ Ajax */
//    @GetMapping("/program")
//    public List<ProgramDTO> program() {
//        return programService.programAllList();
//    }

    /* ####################################################################################################### */

    /* 프로그램 _ Keyword 로 검색시 해당 */
//    @GetMapping("/program?enter={keyword}")
//    public String program(Model model, @PathVariable String keyword){
//        /*model.addAttribute("keyword",keyword);*/
//        model.addAttribute("searchPrograms", programService.searchProgram(keyword));
//        return "/app/program/program";
//    }

    /* 프로그램 _ 처음 시작 했을 때 _ 전체 */
//    @GetMapping("/{program}")
//    public String program(){
//        return "app/program/program";
//    }
    /* 프로그램 _ ProgramStatus _ 모집중 */
    /* 프로그램 _ ProgramStatus _ 모집 완료 */
    /* 프로그램 _ ProgramStatus _ 모집 예정 */
    /* 프로그램 _ ProgramStatus _ 모집 마감 */


}
