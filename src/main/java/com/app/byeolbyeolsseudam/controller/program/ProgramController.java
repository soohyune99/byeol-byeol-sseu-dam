package com.app.byeolbyeolsseudam.controller.program;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/program/*")
public class ProgramController {

    /* 프로그램 기본 List */
    @GetMapping("/list")
    public String program(ProgramDTO programDTO){
        return "/app/program/program"; // 이동할 html 파일 경로
    }

    /* 프로그램 DETAIL 창 */
    @GetMapping("/programdetail")
    public String programDetail() {
        return "/app/program/programDetail"; // 이동할 html 파일 경로
    }

    /* ####################################################################################################### */
    /* ##################################  페이지 이동시 사용  ################################################## */
    /* ####################################################################################################### */

//    private final ProgramService programService;

    /* 프로그램 기본 List - 페이지 이동시 사용*/
//    @GetMapping("/list")
//    public String program(ProgramDTO programDTO){
//        model.addAttribute("programs", programService.programAllList());
//        return "/app/program/program";
//    }

    /* 프로그램 Search List _ Keyword 검색 - 페이지 이동 */
//    @GetMapping("/list/search")
//    public String programSearchKeyword(String keyword, Model model){
//        model.addAttribute("programs", programService.searchProgram(keyword));
//        return "/app/program/program";
//    }


    /* 프로그램 상태 선택시 - 페이지 이동(status부분 클릭시 css 유지가 안됌... default값 전체로 계속 유지됨. -> 사용시 HTML 더 만들어서 사용하기  */
//    @GetMapping("/list/status")
//    public String programStatus(ProgramStatus programStatus, Model model){
//        model.addAttribute("programs", programService.programStatusIngList(programStatus));
//        return "/app/program/program";
//    }


    /* ####################################################################################################### */
    /* ###################################  사용 안함  ######################################################### */
    /* ####################################################################################################### */

//    /* 전체 program List _ Ajax */
//    @GetMapping("/program")
//    public List<ProgramDTO> program() {
//        return programService.programAllList();
//    }

    /* 프로그램 _ Keyword 로 검색시 해당 - 실패 */
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
