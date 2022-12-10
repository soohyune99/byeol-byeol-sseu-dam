//package com.app.byeolbyeolsseudam.controller.program;
//
//import com.app.byeolbyeolsseudam.domain.Search;
//import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
//import com.app.byeolbyeolsseudam.service.program.ProgramService;
//import com.app.byeolbyeolsseudam.type.ProgramStatus;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/pro/*")
//@Slf4j
//public class ProgramRestController {
//    private final ProgramService programService;
//
//    /* default List _ 프로그램 들어갔을 때 실행 / 전체 클릭했을 때 실행  */
//    @GetMapping("/list")
//    public List<ProgramDTO> getList(){
//        List<ProgramDTO> programs = programService.programAllList();
//        return programs;
//    }
//
//    /* Status List _ 프로그램 상태 클릭시 실행되는 List */
//    @GetMapping("/list/{programStatus}")
//    public List<ProgramDTO> statusList(@PathVariable ProgramStatus programStatus){
//        List<ProgramDTO> programs = programService.programStatusIngList(programStatus);
//        log.info("dfsdfsdfadsfa: " + programs);
//        return programs;
//    }
//
//    /* Keyword List _ 검색시 실행되는 List */
//    @PostMapping("/list/{keyword}")
//    public List<ProgramDTO> searchKeywordList(@PathVariable String keyword){
//        List<ProgramDTO> programs = programService.searchProgram(keyword);
//        return programs;
//    }
//
//    @GetMapping("/scroll/{page}")
//    public Page<ProgramDTO> infiniteScroll(@PathVariable("page")Integer page, @PageableDefault(size = 12) Pageable pageable, String keyword, String programStatus){
//        log.info("|||  keyword : " + keyword);
//        log.info("|||  programStatus : " + programStatus);
//
//        Search search = new Search();
//        search.setKeyword(keyword);
//        search.setProgramStatus(programStatus);
//        pageable=PageRequest.of(page,pageable.getPageSize(), Sort.Direction.DESC, "programId");
//
//        log.info("???? search.getKeyword : " + search.getKeyword());
//
//        log.info("???? search.setProgramStatus : " + search.getProgramStatus());
//        log.info("PAGE : " + page);
//        log.info("!!!!!!! pageable !!!!!!!!  : "+ pageable);
//        log.info("!!!!!!! getPageSize !!!!!  : "+ pageable.getPageSize());
//        log.info("!!!!!!! getOffset !!!!!!!  : "+ pageable.getOffset());
//        log.info("!!!!!!! PageNumber !!!!!!  : "+ pageable.getPageNumber());
//
//        Page<ProgramDTO> programs = programService.selectScrollPrograms(search, pageable);
//        log.info(pageable.getPageNumber()+" :::: PageNumber");
//
//        log.info(programs.getContent().size() + " :::: getCountSize ");
//        return programs;
//    }
//
//}
