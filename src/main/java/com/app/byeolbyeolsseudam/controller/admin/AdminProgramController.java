package com.app.byeolbyeolsseudam.controller.admin;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.service.admin.AdminProgramService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = {"/admin/program/*", "/admin/program"})
@Slf4j
public class AdminProgramController {
    /* 프로그램 관리 - 프로그램 목록 */

    private final AdminProgramService adminProgramService;

    @GetMapping("/{page}")
    public String adminProgram(@PathVariable("page") Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "programId");

        model.addAttribute("programs",adminProgramService.searchProgram(pageable));
        return "/app/admin/adminProgram";
    }

    @GetMapping("")
    public String adminProgramSearch(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer page, Model model){
        page = Optional.ofNullable(page).orElse(1);
        keyword = Optional.ofNullable(keyword).orElse("");

        Pageable pageable = PageRequest.of(page-1,10, Sort.Direction.DESC, "programId");

        model.addAttribute("keyword", keyword);
        model.addAttribute("programs", adminProgramService.searchProgramPaging(keyword, pageable));
        model.addAttribute("programsCount", adminProgramService.searchProgramPaging(keyword, pageable).getTotalElements());
        return "/app/admin/adminProgram";
    }


    /* 프로그램 - 프로그램 세부 내역 */
    @GetMapping("/detail")
    public String adminProgramDetail(@RequestParam(name = "programId") String programId, Model model){
        model.addAttribute("program", adminProgramService.selectById(programId));
        model.addAttribute("registers", adminProgramService.showRegisterList(programId));

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
        return new RedirectView("/admin/program/1");
    }

    /* 프로그램 관리 - 프로그램 목록 + 프로그램 수정 */
    @GetMapping("/modify")
    public String adminProgramModify(@RequestParam(name = "programId") String programId, Model model){
        model.addAttribute("oldProgram", adminProgramService.selectById(programId));

        return "/app/admin/adminProgramModify";
    }

    @PostMapping("/modified")
    public RedirectView adminProgramModified(ProgramDTO programDTO){
        adminProgramService.updateProgram(programDTO, programDTO.getProgramId());
        return new RedirectView("/admin/program/1");
    }


    /* 프로그램 관리 - 프로그램 삭제 */
    @PostMapping("/delete")
    public RedirectView adminProgramDelete(@RequestParam List<String> checkedValue){
        adminProgramService.removeProgram(checkedValue);
        return new RedirectView("/admin/program/1");
    }
}
