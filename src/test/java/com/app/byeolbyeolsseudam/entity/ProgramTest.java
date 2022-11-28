package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProgramDTO;
import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.ProgramRepository;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveProgramTest(){
        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setOpeningDate(LocalDateTime.of(2021, 11,12,11,00));
        programDTO.setClosingDate(LocalDateTime.of(2021, 12,12,12,00));
        programDTO.setProgramDate(LocalDateTime.of(2021,12,23,12,00));
        programDTO.setProgramName("작년줍깅의 유래와 이해");
        programDTO.setProgramLimitCount(35);
        programDTO.setProgramPlace("역삼역 3번 출구");
        programDTO.setProgramStatus(ProgramStatus.마감);
        programDTO.setProgramTime(5);
        programDTO.setProgramFile("jub.img");
        programDTO.setProgramFileDetail("Detail.img");
        programDTO.setProgramContent("줍깅");

        Program program = programDTO.toEntity();
        programRepository.save(program);

    }

    @Test
    public void findTest(){
        Optional<Program> findProgram = programRepository.findById(8L);
        if(findProgram.isPresent()){
            Assertions.assertThat(findProgram.get().getProgramId().equals(8L));
        }
    }

    @Test
    public void updateTest(){
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setClosingDate(LocalDateTime.of(2022,11,11,11,11));
        possibleDate.setOpeningDate(LocalDateTime.of(2022,10,10,10,10));
        programRepository.findAll().get(2).update("수정된 줍깅 X 쓰담","미금역", possibleDate,5,LocalDateTime.of(2022,10,10,10,10),"수정된",
                4,ProgramStatus.모집중,"9090.img","eeee.img");
    }

//    @Test
//    public void deleteTest(){
//        programRepository.deleteById(7L);
//    }
}