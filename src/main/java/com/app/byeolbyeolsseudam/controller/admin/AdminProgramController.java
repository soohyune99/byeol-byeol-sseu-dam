package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.service.admin.AdminProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/program/*", "/admin/program"})
@Slf4j
public class AdminProgramController {
    /* 프로그램 관리 - 프로그램 목록 */

    private final AdminProgramService adminProgramService;

    @GetMapping("")
    public String adminProgram(Model model){
        model.addAttribute("programs",adminProgramService.showList());
        return "/app/admin/adminProgram";
    }

    /* 프로그램 - 프로그램 세부 내역 */
    @GetMapping("/detail")
    public String adminProgramDetail(){
        return "/app/admin/adminProgramDetail";
    }

    /* 프로그램 관리 - 프로그램 목록 + 프로그램 추가 */
    @GetMapping("/add")
    public String adminProgramAdd(){
        return "/app/admin/adminProgramAdd";
    }

    @PostMapping("/save")
    public RedirectView adminProgramSave(ProgramDTO programDTO) throws DateTimeParseException {
        adminProgramService.saveProgram(programDTO);
        return new RedirectView("/admin/program");
    }

    /* 프로그램 관리 - 프로그램 목록 + 프로그램 수정 */
    @GetMapping("/modify")
    public String adminProgramModify(){
        return "/app/admin/adminProgramModify";
    }

    /* 프로그램 관리 - 프로그램 삭제 */
    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminProgramService.removeProgram(checkedValue);
        return new RedirectView("/admin/program");
    }
}
