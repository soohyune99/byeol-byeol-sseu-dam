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

    /* 원래
        @GetMapping("/dynamic/{page}/{keyword}/{programStatus}") 로 했을 때 매핑이 전혀되지 않고 404가 떠서
        다른 문제인지 확인 하기 위해 처음 들어왔을때 확인하는 페이지 경로 만들었었음.(아래)

        @GetMapping("/dynamic/{page}")
        public Page<ProgramDTO> programDynamicListDefault(@PageableDefault(size = 12)Pageable pageable, @PathVariable("page")Integer page){
            pageable = PageRequest.of(page,pageable.getPageSize(), Sort.Direction.DESC,"programDate");
            Page<ProgramDTO> programs = programDynamicService.programDynamicList("","",pageable);
            return programs;
        }

        처음에는 경로를 하나로만 작성하면 알아서 경로가 생기는 걸로 인지 했었음 하지만 그게 아니었음. 여러가지 경우의 수 대로 나누어주었어야 했고

        "/dynamic/{page}/{keyword}"
        "/dynamic/{page}/{programStatus}"
        => 이 두개는 동일 경로로 인식. 이것을 구분해줄 작업이 필요했음

        "/dynamic/{page}/status/{programStatus}" 가운데에 status라는 것을 넣어주어
        키워드만 입력되었을때, 상태 선택이 되었을 때 2가지로 분리 되는 것을 할 수 있었음.
    */

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