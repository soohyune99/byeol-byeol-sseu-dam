package com.app.byeolbyeolsseudam.service;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.service.program.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {
    @Autowired
    ProgramService programService;

    /* keyword 검색에 따라서 결과 List */
    @Test
    public void searchProgramTest(){
        String keyword = "아이와 함께";
        log.info("검색 결과 : " + programService.searchProgram(keyword));
    }

    /*  전체 program List _ Ajax  */
    @Test
    public void programAllListTest(){
        programService.programAllList().stream().map(ProgramDTO::toString).forEach(log::info);
    }
}
