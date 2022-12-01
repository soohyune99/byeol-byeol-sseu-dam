package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.program.ProgramCustomRepository;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    ProgramCustomRepository programCustomRepository;

    @Autowired
    ProgramRepository programRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveProgramTest(){
        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setProgramName("3333333줍깅의 유래와 이해");
        programDTO.setProgramPlace("역삼역 3번 출구");
        programDTO.setOpeningDate(LocalDateTime.of(2021, 11,12,11,00));
        programDTO.setClosingDate(LocalDateTime.of(2021, 12,12,12,00));
        programDTO.setProgramTime(5);
        programDTO.setProgramDate(LocalDateTime.of(2021,12,23,12,00));
        programDTO.setProgramContent("줍깅");
        programDTO.setProgramLimitCount(35);
        programDTO.setProgramStatus(ProgramStatus.마감);
        programDTO.setProgramFileProfileName("jub.img");
        programDTO.setProgramFileProfilePath("ProfilePath");
        programDTO.setProgramFileProfileUuid("ProfileUuid");
        programDTO.setProgramFileDetailName("Detail.img");
        programDTO.setProgramFileDetailPath("DetailPath");
        programDTO.setProgramFileDetailUuid("DetailUuid");

        Program program = programDTO.toEntity();
        programRepository.save(program);

    }

    @Test
    public void findTest(){
        Optional<Program> findProgram = programRepository.findById(11L);
        if(findProgram.isPresent()){
            Assertions.assertThat(findProgram.get().getProgramId().equals(11L));
        }
    }

    @Test
    public void updateTest(){
        ProgramDTO programDTO = new ProgramDTO();
        Program program= programRepository.findById(1L).get();

        programDTO.setProgramName("수정작년줍깅의 유래와 이해");
        programDTO.setProgramPlace("수정역삼역 3번 출구");
        programDTO.setOpeningDate(LocalDateTime.of(2021, 11, 12, 11, 00));
        programDTO.setClosingDate(LocalDateTime.of(2021, 12, 12, 12, 00));
        programDTO.setProgramTime(3);
        programDTO.setProgramDate(LocalDateTime.of(2021, 12, 23, 12, 00));
        programDTO.setProgramContent("줍깅");
        programDTO.setProgramLimitCount(35);
        programDTO.setProgramStatus(ProgramStatus.마감);
        programDTO.setProgramFileProfileName("수정jub.img");
        programDTO.setProgramFileProfilePath("수정ProfilePath");
        programDTO.setProgramFileProfileUuid("수정ProfileUuid");
        programDTO.setProgramFileDetailName("수정Detail.img");
        programDTO.setProgramFileDetailPath("수정DetailPath");
        programDTO.setProgramFileDetailUuid("수정DetailUuid");

        program.update(programDTO);

    }

    @Test
    public void findAllSearchTest(){
        programCustomRepository.findAllSearch("줍깅").stream().map(ProgramDTO::toString).forEach(log::info);
        log.info("aeffa");
    }


    @Test

    public void deleteTest(){
        programRepository.deleteById(1L);
    }
}