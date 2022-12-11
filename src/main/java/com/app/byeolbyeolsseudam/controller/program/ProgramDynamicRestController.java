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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prod/*")
@Slf4j
public class ProgramDynamicRestController {
    private final ProgramDynamicService programDynamicService;

//    @GetMapping("/dynamic/{page}")
//    public Page<ProgramDTO> programDynamicListDefault(@PageableDefault(size = 12)Pageable pageable, @PathVariable("page")Integer page){
//        pageable = PageRequest.of(page,pageable.getPageSize(), Sort.Direction.DESC,"programDate");
//        Page<ProgramDTO> programs = programDynamicService.programDynamicList("","",pageable);
//
//        log.info("--------- showStart ----------");
//        log.info("showStart !!! pageable !!!!!!  : "+ pageable);
//        log.info("showStart !!! getPageSize !!!  : "+ pageable.getPageSize());
//        log.info("showStart !!! getOffset !!!!!  : "+ pageable.getOffset());
//        log.info("showStart !!! PageNumber !!!!  : "+ pageable.getPageNumber());
//        log.info("-------------------");
//        log.info("showStart ||| PAGE |||||||||||  : "+ page);
//        log.info("-------------------");
//
//        return programs;
//    }

//    @GetMapping("/dynamic/{page}/{keyword}/{programStatus}")
    @GetMapping(value = {"/dynamic/{page}", "/dynamic/{page}/{keyword}", "/dynamic/{page}/{keyword}/{programStatus}", "/dynamic/{page}/status/{programStatus}"})
    public Page<ProgramDTO> programDynamicList(@PathVariable(value = "keyword", required = false)String keyword, @PathVariable(value = "programStatus", required = false) String programStatus, @PathVariable("page")Integer page, @PageableDefault(size = 12)Pageable pageable){
        pageable = PageRequest.of(page,pageable.getPageSize(), Sort.Direction.DESC,"programDate");
        Page<ProgramDTO> programs = programDynamicService.programDynamicList(keyword, programStatus, pageable);


        log.info("-------------------");
        log.info("!!! pageable !!!!!!  : "+ pageable);
        log.info("!!! getSort !!!!!!  : "+ pageable.getSort());
        log.info("!!! getPageSize !!!  : "+ pageable.getPageSize());
        log.info("!!! getOffset !!!!!  : "+ pageable.getOffset());
        log.info("!!! PageNumber !!!!  : "+ pageable.getPageNumber());
        log.info("-------------------");
        log.info("||| PAGE |||||||||||  : "+ page);
        log.info("||| KEYWORD |||||||| : " + keyword);
        log.info("||| PROGRAMSTATUS || : " + programStatus);
        log.info("||| programs || : " + programs);
        log.info("||| getTotalElements || : " + programs.getTotalElements());
        log.info("-------------------");

        return programs;
    }


}