package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.entity.myprogram.Myprogram;
import com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.myprogram.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.program.ProgramRepository;
import com.app.byeolbyeolsseudam.type.MyprogramStatus;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.app.byeolbyeolsseudam.entity.myprogram.QMyprogram.myprogram;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MyprogramTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MyprogramRepository myprogramRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MyprogramDTO myprogramDTO = new MyprogramDTO();
//        ProgramDTO programDTO = new ProgramDTO();
//
//        programDTO.setProgramName("");
//        programDTO.setProgramStatus(ProgramStatus.마감);
//        programDTO.setProgramTime(3);
//        programDTO.setProgramLimitCount(10);
//        programDTO.setProgramPlace("역삼역");
//        programDTO.setProgramDate(LocalDateTime.of(2022, 11, 27, 0, 0, 0));
//        programDTO.setOpeningDate(LocalDateTime.of(2022,12,1,0,0,0));
//        programDTO.setClosingDate(LocalDateTime.of(2022, 12, 5, 0, 0,0, 0));
//
//        programRepository.save(programDTO.toEntity());

        myprogramDTO.setMyprogramStatus(MyprogramStatus.수강완료);
        Myprogram myprogram = myprogramDTO.toEntity();
        myprogram.changeMember(memberRepository.findById(1L).get());
        myprogram.changeProgram(programRepository.findAll().get(3));
        myprogramRepository.save(myprogram);
    }

    @Test
    public void findTest(){
        jpaQueryFactory.select(myprogram.myprogramId).from(myprogram)
                .orderBy(myprogram.myprogramId.desc())
                .fetch()
                .stream().forEach(m -> log.info("Id : " + m));
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(myprogram)
                .orderBy(myprogram.myprogramId.desc())
                .limit(1)
                .fetchOne();
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(myprogram)
                .where(myprogram.program.programId.eq(12L))
                .execute();
    }
}
