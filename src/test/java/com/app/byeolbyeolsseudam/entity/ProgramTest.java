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

        programDTO.setProgramName("작년줍깅의 유래와 이해");
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

    //save 테스트에서 프로필 Path 랑 UUID null들어감
    // 근데 수정하면 잘됨.

    @Test
    public void findTest(){
        Optional<Program> findProgram = programRepository.findById(11L);
        if(findProgram.isPresent()){
            Assertions.assertThat(findProgram.get().getProgramId().equals(11L));
        }
    }

    @Test
    public void updateTest(){
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setClosingDate(LocalDateTime.of(2022,11,11,11,11));
        possibleDate.setOpeningDate(LocalDateTime.of(2022,10,10,10,10));
        programRepository.findAll().get(1).update(
                "수정된 줍깅 X 쓰담",
                "미금역",
                possibleDate,
                5,
                LocalDateTime.of(2022,10,10,10,10),
                "수정된 내용",
                3,
                ProgramStatus.모집중,
                "9090.img",
                "eeee.img",
                "eeee.img",
                "eeee.img",
                "eeee.img",
                "ffff");
    }

    @Test
    public void deleteTest(){
        programRepository.deleteById(10L);
    }
}