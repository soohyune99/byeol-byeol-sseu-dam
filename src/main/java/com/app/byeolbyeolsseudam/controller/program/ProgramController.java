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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/program/*")
@Slf4j
public class ProgramController {
    private final ProgramService programService;

    /* 프로그램 기본 List */
    @GetMapping("/list")
    public String program(){
        return "/app/program/program"; // 이동할 html 파일 경로
    }

    /* 프로그램 DETAIL 이동 */
    @GetMapping("/detail")
    public String findProgramDetail(Long programId, Model model, HttpSession session){
        if(Optional.ofNullable(session.getAttribute("member")).isPresent()){
            MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
            Long memberId = memberDTO.getMemberId();
            model.addAttribute("check", programService.checkMemberAndProgram(programId, memberId)); // 세션 멤버아이디
        }

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

}
