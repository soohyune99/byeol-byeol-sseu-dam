package com.app.byeolbyeolsseudam.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {

//    @Autowired
//    ProgramDynamicService programDynamicService;
//    public void programDynamicListTest(){
//        programDynamicService.programDynamicList("친환경","",);
//    }


//    @Autowired
//    ProgramService programService;
//
//    /* keyword 검색에 따라서 결과 List */
//    @Test
//    public void searchProgramTest(){
//        String keyword = "아이와 함께";
//        log.info("검색 결과 : " + programService.searchProgram(keyword));
//    }
//
//    /*  전체 program List */
//    @Test
//    public void programAllListTest(){
//        programService.programAllList().stream().map(ProgramDTO::toString).forEach(log::info);
//    }
//
//
//    /* program List _ STATUS _ 모집중 */
//    @Test
//    public void programStatusIngList() {
//        programService.programStatusIngList(ProgramStatus.모집중).stream().map(ProgramDTO::toString).forEach(log::info);
//    }

}
