package com.app.byeolbyeolsseudam.controller.program;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.service.program.ProgramDynamicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prod/*")
@Slf4j
public class ProgramDynamicRestController {
    private final ProgramDynamicService programDynamicService;

    @GetMapping("/dynamic/{keyword}/{programStatus}/{page}")
    public Page<ProgramDTO> programDynamicList(@PathVariable("keyword")String keyword, @PathVariable("programStatus") String programStatus, @PathVariable("page")Integer page, @PageableDefault(size = 12)Pageable pageable){
        Page<ProgramDTO> programs = programDynamicService.programDynamicList(keyword, programStatus, pageable);
        pageable = PageRequest.of(page,pageable.getPageSize(), Sort.Direction.DESC,"programDate");

        log.info("-------------------");
        log.info("!!! pageable !!!!!!  : "+ pageable);
        log.info("!!! getPageSize !!!  : "+ pageable.getPageSize());
        log.info("!!! getOffset !!!!!  : "+ pageable.getOffset());
        log.info("!!! PageNumber !!!!  : "+ pageable.getPageNumber());
        log.info("-------------------");
        log.info("||| PAGE |||||||||||  : "+ page);
        log.info("||| KEYWORD |||||||| : " + keyword);
        log.info("||| PROGRAMSTATUS || : " + programStatus);
        log.info("-------------------");
        return programs;
    }
}