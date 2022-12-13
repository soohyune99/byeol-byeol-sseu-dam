package com.app.byeolbyeolsseudam.controller.program;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.program.ProgramDynamicService;
import com.app.byeolbyeolsseudam.service.program.ProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/program/*")
@Slf4j
public class ProgramController {
    private final ProgramService programService;
    private final ProgramDynamicService programDynamicService;

    /* 프로그램 기본 List */
    @GetMapping("/list")
    public String program(ProgramDTO programDTO){
        return "/app/program/program"; // 이동할 html 파일 경로
    }

    /* 프로그램 DETAIL 이동 */
    @GetMapping("/detail")
    public String findProgramDetail(Long programId, Model model){
        model.addAttribute("program",programService.findProgramDetail(programId));
        return"/app/program/programDetail";
    }

    /* 프로그램 멤버 신청 */
    @GetMapping("/saveMember")
    public RedirectView programSaveMember(Long programId, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        Long memberId = memberDTO.getMemberId();

        log.info(memberId + "");

        programService.programMemberSave(programId, memberId); // 로그인 완성시 사용
        return new RedirectView("/mypage/program");
        // 마이페이지 _ 프로그램 신청내역페이지 컨트롤러로 이동
        // (실제로 마이페이지는 Ajax라서 문서이동만 해도 되지만 구현전 우리는 모름)
        // 내 DB에도 저장되고 상대 컨트롤러로 이동해서 DB에 저장된 것으로 확인하기 위해 RedirectView를 사용
        // return"/app/program/programDetail"; 이런식은 DB안들리고 그냥 페이지에 저장전으로 보일 것 (단순문서이동)
        // 방금 저장된 것을 바탕으로 RedirectView를 하면 DB를 들렸다가 오기 때문에 저장하는 것과 저장된 것을 보여주는 행위를 동시에 할 수 있음.
    }

//    @GetMapping("/scroll/{page}")
//    public List<ProgramDTO> infiniteScroll(@PathVariable int page){
//        return programService.selectScrollPrograms(page);
//    }

//    /* 로그인 되었을때 진행 필요  */
//    @GetMapping("/programAddSuccess")
//    public RedirectView programAddSuccess(@SessionAttribute("member") Member member){
//        log.info(member.getMemberId().toString());
//        return new RedirectView("list");
//    }




//        log.info(member.getMemberId().toString());

//    /* 프로그램 DETAIL 창 */
//    @GetMapping("/detail")
//    public String programDetail() {
//        return "/app/program/programDetail"; // 이동할 html 파일 경로
//    }
//    @GetMapping("/detail?programId={programId}")
//    public String programDetail(Model model, @SessionAttribute("member") Member member, @PathVariable("programId") Long programId) {
//        model.addAttribute("program",programService.programDetailPage(model, member, programId));
//        model.addAttribute("program",programId);
//        return "/app/program/programDetail"; // 이동할 html 파일 경로
//    }
//    @GetMapping("/detail")
//    public String programDetail(Model model){
//        model.addAttribute("detailProgram",programDetail(model));
////        programService.programDetailPage1(programDTO.getProgramId());
//        return "/app/program/programDetail";
//    }
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
