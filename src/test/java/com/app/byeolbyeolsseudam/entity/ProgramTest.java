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
    ProgramRepository programRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void saveProgramTest(){
        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setProgramName("“ 맛있게 먹고 있나요? ” 별별쓰담 X 쓰담학교 지구를 위한 친환경요리 프로젝트 - 일식요리 - ");
        programDTO.setProgramPlace("역삼역 3번 출구");
        programDTO.setOpeningDate(LocalDateTime.of(2022, 5,12,11,00));
        programDTO.setClosingDate(LocalDateTime.of(2022, 9,12,12,00));
        programDTO.setProgramTime(5);
        programDTO.setProgramDate(LocalDateTime.of(2022,12,23,12,00));
        programDTO.setProgramContent("줍깅");
        programDTO.setProgramLimitCount(25);
//        programDTO.setProgramStatus(ProgramStatus.모집예정);
        programDTO.setProgramFileProfileName("jub.img");
        programDTO.setProgramFileProfilePath("/images/program/program3.png");
        programDTO.setProgramFileProfileUuid("ProfileUuid");
        programDTO.setProgramFileDetailName("Detail.img");
        programDTO.setProgramFileDetailPath("/images/program/program31.png");
        programDTO.setProgramFileDetailUuid("DetailUuid");

        Program program = programDTO.toEntity();
        programRepository.save(program);

    }

    @Test
    public void findTest(){
        Optional<Program> findProgram = programRepository.findById(11L);
        if(findProgram.isPresent()){
            Assertions.assertThat(findProgram.get().getProgramId().equals(11L));
            log.info("11번 : "+findProgram.get().getProgramId().equals(11L));
        }

        Program program = findProgram.get();
        log.info("ProgramStatus"+program.getProgramStatus());

    }

    @Test
    public void updateTest(){
        ProgramDTO programDTO = new ProgramDTO();
        Program program= programRepository.findById(2L).get();

        programDTO.setProgramName("“ 맛있게 먹고 있나요? ” 별별쓰담 X 쓰담학교 지구를 위한 친환경요리 프로젝트 - 중화요리 -");
        programDTO.setProgramPlace("수정역삼역 3번 출구");
        programDTO.setOpeningDate(LocalDateTime.of(2023, 11, 12, 11, 00));
        programDTO.setClosingDate(LocalDateTime.of(2024, 12, 12, 12, 00));
        programDTO.setProgramTime(3);
        programDTO.setProgramDate(LocalDateTime.of(2021, 12, 23, 12, 00));
        programDTO.setProgramContent("-지구를 위하는 친환경요리를 함께 만들어보고 맛보는 시간을 만들어보아요 지구를 아끼고 맛있는 음식도 먹고!");
        programDTO.setProgramLimitCount(35);
        programDTO.setProgramStatus(ProgramStatus.모집완료);
        programDTO.setProgramFileProfileName("수정jub.img");
        programDTO.setProgramFileProfilePath("/images/program/program1.png");
        programDTO.setProgramFileProfileUuid("수정ProfileUuid");
        programDTO.setProgramFileDetailName("수정Detail.img");
        programDTO.setProgramFileDetailPath("/images/program/program1-1.png");
        programDTO.setProgramFileDetailUuid("수정DetailUuid");

        program.update(programDTO);

    }

    /* 프로그램 검색 _ 프로그램 제목 _ 키워드 검색 */
    @Test
    public void searchProgramTest(){
        programRepository.searchProgram("줍깅").stream().map(ProgramDTO::toString).forEach(log::info);
    }

    @Test
    public void deleteTest(){
        programRepository.deleteById(1L);
    }

}