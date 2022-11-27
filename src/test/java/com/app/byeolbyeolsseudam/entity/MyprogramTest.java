package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MemberDTO;
import com.app.byeolbyeolsseudam.domain.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.ProgramRepository;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MyprogramTest {
    @Autowired
    private MyprogramRepository myprogramRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MyprogramDTO myprogramDTO = new MyprogramDTO();
        ProgramDTO programDTO = new ProgramDTO();

        programDTO.setProgramName("원데이 업사이클링");
        programDTO.setProgramStatus(ProgramStatus.모집예정);
        programDTO.setProgramTime(3);
        programDTO.setProgramLimitCount(10);
        programDTO.setProgramPlace("역삼역");
        programDTO.setProgramDate(LocalDateTime.of(2022, 11, 27, 0, 0, 0));
//        programDTO.setOpeningDate(LocalDateTime.of(2022,12,1,0,0,0));
//        programDTO.setClosingDate(LocalDateTime.of(2022, 12, 5, 0, 0,0, 0));

        programRepository.save(programDTO.toEntity());

        myprogramDTO.setProgram(programRepository.findAll().get(0));
        myprogramDTO.setMember(memberRepository.findAll().get(0));

        myprogramRepository.save(myprogramDTO.toEntity());
    }
}
