package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.program.ProgramDTO;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.program.ProgramDynamicRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProgramTest {

//    @Autowired
//    ProgramRepository programRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    ProgramDynamicRepository programDynamicRepository;

//    @Test
//    public void saveProgramTest(){
//        ProgramDTO programDTO01 = new ProgramDTO();
//        ProgramDTO programDTO02 = new ProgramDTO();
//        ProgramDTO programDTO03 = new ProgramDTO();
//        ProgramDTO programDTO04 = new ProgramDTO();
//        ProgramDTO programDTO05 = new ProgramDTO();
//        ProgramDTO programDTO06 = new ProgramDTO();
//        ProgramDTO programDTO07 = new ProgramDTO();
//        ProgramDTO programDTO08 = new ProgramDTO();
//        ProgramDTO programDTO09 = new ProgramDTO();
//        ProgramDTO programDTO10 = new ProgramDTO();
//        ProgramDTO programDTO11 = new ProgramDTO();
//        ProgramDTO programDTO12 = new ProgramDTO();
//
//        programDTO01.setProgramName("B1 친환경요리 만들기1 - 중화요리");
//        programDTO01.setProgramPlace("역삼쓰담빌딩 302호");
//        programDTO01.setOpeningDate(LocalDateTime.of(2022, 11,28,12,00));
//        programDTO01.setClosingDate(LocalDateTime.of(2022, 12,2,12,00));
//        programDTO01.setProgramTime(4);
//        programDTO01.setProgramDate(LocalDateTime.of(2022,12,7,13,00));
//        programDTO01.setProgramContent("“맛있게 먹고 있나요?” " +
//                "별별쓰담 X 쓰담학교" +
//                "지구를 위한 친환경요리 프로젝트지구를 위하는 마음을 담아 " +
//                "별별쓰담에서 쓰담학교를 만들었습니다 " +
//                "지구를 위한 친환경요리를 함께 해보고 " +
//                "맛보는 시간을 만들어보아요" +
//                "지구를 아끼고 맛있는 음식도 먹고! " +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO01.setProgramLimitCount(35);
//        programDTO01.setProgramStatus(ProgramStatus.마감);
//        programDTO01.setProgramFileProfileName("/images/program/program1.png");
//        programDTO01.setProgramFileProfilePath("/images/program/program1.png");
//        programDTO01.setProgramFileProfileUuid("/images/program/program1.png");
//        programDTO01.setProgramFileDetailName("/images/program/program1-1.png");
//        programDTO01.setProgramFileDetailPath("/images/program/program1-1.png");
//        programDTO01.setProgramFileDetailUuid("/images/program/program1-1.png");
//
//        programDTO02.setProgramName("B2 친환경요리 만들기2 - 퓨전요리");
//        programDTO02.setProgramPlace("역삼쓰담빌딩 401호 ");
//        programDTO02.setOpeningDate(LocalDateTime.of(2022, 11,30,12,00));
//        programDTO02.setClosingDate(LocalDateTime.of(2022, 12,7,12,00));
//        programDTO02.setProgramTime(3);
//        programDTO02.setProgramDate(LocalDateTime.of(2022,12,9,14,00));
//        programDTO02.setProgramContent("“맛있게 먹고 있나요?” " +
//                "별별쓰담 X 쓰담학교" +
//                "지구를 위한 친환경요리 프로젝트지구를 위하는 마음을 담아 " +
//                "별별쓰담에서 쓰담학교를 만들었습니다 " +
//                "지구를 위한 친환경요리를 함께 해보고 " +
//                "맛보는 시간을 만들어보아요" +
//                "지구를 아끼고 맛있는 음식도 먹고! " +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO02.setProgramLimitCount(20);
//        programDTO02.setProgramStatus(ProgramStatus.마감);
//        programDTO02.setProgramFileProfileName("/images/program/program2.png");
//        programDTO02.setProgramFileProfilePath("/images/program/program2.png");
//        programDTO02.setProgramFileProfileUuid("/images/program/program2.png");
//        programDTO02.setProgramFileDetailName("/images/program/program2-1.png");
//        programDTO02.setProgramFileDetailPath("/images/program/program2-1.png");
//        programDTO02.setProgramFileDetailUuid("/images/program/program2-1.png");
//
//        programDTO03.setProgramName("B3 친환경요리 만들기3 - 한식");
//        programDTO03.setProgramPlace("역삼쓰담빌딩 102호");
//        programDTO03.setOpeningDate(LocalDateTime.of(2022, 12,5,12,00));
//        programDTO03.setClosingDate(LocalDateTime.of(2022, 12,9,12,00));
//        programDTO03.setProgramTime(4);
//        programDTO03.setProgramDate(LocalDateTime.of(2022,12,11,12,00));
//        programDTO03.setProgramContent("“맛있게 먹고 있나요?” " +
//                "별별쓰담 X 쓰담학교" +
//                "지구를 위한 친환경요리 프로젝트지구를 위하는 마음을 담아 " +
//                "별별쓰담에서 쓰담학교를 만들었습니다 " +
//                "지구를 위한 친환경요리를 함께 해보고 " +
//                "맛보는 시간을 만들어보아요" +
//                "지구를 아끼고 맛있는 음식도 먹고! " +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO03.setProgramLimitCount(35);
//        programDTO03.setProgramStatus(ProgramStatus.마감);
//        programDTO03.setProgramFileProfileName("/images/program/program3.png");
//        programDTO03.setProgramFileProfilePath("/images/program/program3.png");
//        programDTO03.setProgramFileProfileUuid("/images/program/program3.png");
//        programDTO03.setProgramFileDetailName("/images/program/program3-1.png");
//        programDTO03.setProgramFileDetailPath("/images/program/program3-1.png");
//        programDTO03.setProgramFileDetailUuid("/images/program/program3-1.png");
//
//        programDTO04.setProgramName("B4 아이와 함께 만드는 친환경 요리");
//        programDTO04.setProgramPlace("역삼 쓰담빌딩 302호");
//        programDTO04.setOpeningDate(LocalDateTime.of(2022, 11,27,12,00));
//        programDTO04.setClosingDate(LocalDateTime.of(2022, 12,4,12,00));
//        programDTO04.setProgramTime(4);
//        programDTO04.setProgramDate(LocalDateTime.of(2022,12,6,13,00));
//        programDTO04.setProgramContent("“맛있게 먹고 있나요?” " +
//                "별별쓰담 X 쓰담학교" +
//                "아이와 함께하는" +
//                "지구를 위한 친환경요리 프로젝트" +
//                "지구를 위하는 마음을 담아 " +
//                "별별쓰담에서 쓰담학교를 만들었습니다 " +
//                "지구를 위한 친환경요리를 함께 해보고 " +
//                "맛보는 시간을 만들어보아요" +
//                "지구를 아끼고 맛있는 음식도 먹고! " +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO04.setProgramLimitCount(15);
//        programDTO04.setProgramStatus(ProgramStatus.마감);
//        programDTO04.setProgramFileProfileName("/images/program/program4.png");
//        programDTO04.setProgramFileProfilePath("/images/program/program4.png");
//        programDTO04.setProgramFileProfileUuid("/images/program/program4.png");
//        programDTO04.setProgramFileDetailName("/images/program/program4-1.png");
//        programDTO04.setProgramFileDetailPath("/images/program/program4-1.png");
//        programDTO04.setProgramFileDetailUuid("/images/program/program4-1.png");
//
//        programDTO05.setProgramName("B5 아이와 함께 만드는 비건 레시피");
//        programDTO05.setProgramPlace("역삼쓰담빌딩 301호");
//        programDTO05.setOpeningDate(LocalDateTime.of(2022, 11,30,12,00));
//        programDTO05.setClosingDate(LocalDateTime.of(2022, 12,7,12,00));
//        programDTO05.setProgramTime(4);
//        programDTO05.setProgramDate(LocalDateTime.of(2022,12,27,14,30));
//        programDTO05.setProgramContent("“맛있게 먹고 있나요?” " +
//                "별별쓰담 X 쓰담학교" +
//                "아이와 함께하는" +
//                "지구를 위한 친환경요리 프로젝트" +
//                " 지구를 위하는 마음을 담아 " +
//                "별별쓰담에서 쓰담학교를 만들었습니다 " +
//                "지구를 위한 플라스틱 재활용을 알아보고 " +
//                "쓰담수거를 하면 어떻게 되는지 알아보아요" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO05.setProgramLimitCount(15);
//        programDTO05.setProgramStatus(ProgramStatus.마감);
//        programDTO05.setProgramFileProfileName("/images/program/program5.png");
//        programDTO05.setProgramFileProfilePath("/images/program/program5.png");
//        programDTO05.setProgramFileProfileUuid("/images/program/program5.png");
//        programDTO05.setProgramFileDetailName("/images/program/program5-1.png");
//        programDTO05.setProgramFileDetailPath("/images/program/program5-1.png");
//        programDTO05.setProgramFileDetailUuid("/images/program/program5-1.png");
//
//        programDTO06.setProgramName("B6 아이와 함께하는 쓰담수거");
//        programDTO06.setProgramPlace("역삼쓰담빌딩 302호");
//        programDTO06.setOpeningDate(LocalDateTime.of(2022, 12,12,12,00));
//        programDTO06.setClosingDate(LocalDateTime.of(2022, 12,16,12,00));
//        programDTO06.setProgramTime(2);
//        programDTO06.setProgramDate(LocalDateTime.of(2022,12,19,15,00));
//        programDTO06.setProgramContent("줍깅");
//        programDTO06.setProgramLimitCount(16);
//        programDTO06.setProgramStatus(ProgramStatus.마감);
//        programDTO06.setProgramFileProfileName("/images/program/program6.png");
//        programDTO06.setProgramFileProfilePath("/images/program/program6.png");
//        programDTO06.setProgramFileProfileUuid("/images/program/program6.png");
//        programDTO06.setProgramFileDetailName("/images/program/program6-1.png");
//        programDTO06.setProgramFileDetailPath("/images/program/program6-1.png");
//        programDTO06.setProgramFileDetailUuid("/images/program/program6-1.png");
//
//        programDTO07.setProgramName("B7 ZERO WASTE & 쓰담 2");
//        programDTO07.setProgramPlace("역삼쓰담빌딩 503호");
//        programDTO07.setOpeningDate(LocalDateTime.of(2022, 11,28,12,00));
//        programDTO07.setClosingDate(LocalDateTime.of(2022, 12,2,12,00));
//        programDTO07.setProgramTime(2);
//        programDTO07.setProgramDate(LocalDateTime.of(2022,12,6,12,00));
//        programDTO07.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO07.setProgramLimitCount(30);
//        programDTO07.setProgramStatus(ProgramStatus.마감);
//        programDTO07.setProgramFileProfileName("/images/program/program7.png");
//        programDTO07.setProgramFileProfilePath("/images/program/program7.png");
//        programDTO07.setProgramFileProfileUuid("/images/program/program7.png");
//        programDTO07.setProgramFileDetailName("/images/program/program7-1.png");
//        programDTO07.setProgramFileDetailPath("/images/program/program7-1.png");
//        programDTO07.setProgramFileDetailUuid("/images/program/program7-1.png");
//
//        programDTO08.setProgramName("B8 ZERO WASTE & 쓰담 3");
//        programDTO08.setProgramPlace("역삼쓰담빌딩 301호");
//        programDTO08.setOpeningDate(LocalDateTime.of(2022, 12,5,12,00));
//        programDTO08.setClosingDate(LocalDateTime.of(2022, 12,9,12,00));
//        programDTO08.setProgramTime(2);
//        programDTO08.setProgramDate(LocalDateTime.of(2022,12,13,15,00));
//        programDTO08.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO08.setProgramLimitCount(30);
//        programDTO08.setProgramStatus(ProgramStatus.마감);
//        programDTO08.setProgramFileProfileName("/images/program/program8.png");
//        programDTO08.setProgramFileProfilePath("/images/program/program8.png");
//        programDTO08.setProgramFileProfileUuid("/images/program/program8.png");
//        programDTO08.setProgramFileDetailName("/images/program/program8-1.png");
//        programDTO08.setProgramFileDetailPath("/images/program/program8-1.png");
//        programDTO08.setProgramFileDetailUuid("/images/program/program8-1.png");
//
//        programDTO09.setProgramName("B9 ZERO WASTE & 쓰담 4");
//        programDTO09.setProgramPlace("역삼쓰담빌딩 203호");
//        programDTO09.setOpeningDate(LocalDateTime.of(2022, 12,12,12,00));
//        programDTO09.setClosingDate(LocalDateTime.of(2022, 12,16,12,00));
//        programDTO09.setProgramTime(4);
//        programDTO09.setProgramDate(LocalDateTime.of(2022,12,20,15,00));
//        programDTO09.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO09.setProgramLimitCount(35);
//        programDTO09.setProgramStatus(ProgramStatus.마감);
//        programDTO09.setProgramFileProfileName("/images/program/program9.png");
//        programDTO09.setProgramFileProfilePath("/images/program/program9.png");
//        programDTO09.setProgramFileProfileUuid("/images/program/program9.png");
//        programDTO09.setProgramFileDetailName("/images/program/program9-1.png");
//        programDTO09.setProgramFileDetailPath("/images/program/program9-1.png");
//        programDTO09.setProgramFileDetailUuid("/images/program/program9-1.png");
//
//        programDTO10.setProgramName("B10 ZERO WASTE & 쓰담 5");
//        programDTO10.setProgramPlace("역삼쓰담빌딩 303호");
//        programDTO10.setOpeningDate(LocalDateTime.of(2022, 12,26,12,00));
//        programDTO10.setClosingDate(LocalDateTime.of(2022, 12,30,12,00));
//        programDTO10.setProgramTime(2);
//        programDTO10.setProgramDate(LocalDateTime.of(2023,1,3,12,00));
//        programDTO10.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO10.setProgramLimitCount(30);
//        programDTO10.setProgramStatus(ProgramStatus.마감);
//        programDTO10.setProgramFileProfileName("/images/program/program10.png");
//        programDTO10.setProgramFileProfilePath("/images/program/program10.png");
//        programDTO10.setProgramFileProfileUuid("/images/program/program10.png");
//        programDTO10.setProgramFileDetailName("/images/program/program10-1.png");
//        programDTO10.setProgramFileDetailPath("/images/program/program10-1.png");
//        programDTO10.setProgramFileDetailUuid("/images/program/program10-1.png");
//
//        programDTO11.setProgramName("B11 ZERO WASTE & 쓰담 6");
//        programDTO11.setProgramPlace("역삼쓰담빌딩 301호");
//        programDTO11.setOpeningDate(LocalDateTime.of(2023, 1,2,12,00));
//        programDTO11.setClosingDate(LocalDateTime.of(2023, 1,6,12,00));
//        programDTO11.setProgramTime(2);
//        programDTO11.setProgramDate(LocalDateTime.of(2023,1,10,12,00));
//        programDTO11.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요");
//        programDTO11.setProgramLimitCount(30);
//        programDTO11.setProgramStatus(ProgramStatus.마감);
//        programDTO11.setProgramFileProfileName("/images/program/program11.png");
//        programDTO11.setProgramFileProfilePath("/images/program/program11.png");
//        programDTO11.setProgramFileProfileUuid("/images/program/program11.png");
//        programDTO11.setProgramFileDetailName("/images/program/program11-1.png");
//        programDTO11.setProgramFileDetailPath("/images/program/program11-1.png");
//        programDTO11.setProgramFileDetailUuid("/images/program/program11-1.png");
//
//        programDTO12.setProgramName("B12 ZERO WASTE & 쓰담 SPECIAL");
//        programDTO12.setProgramPlace("역삼쓰담빌딩 아트홀 ");
//        programDTO12.setOpeningDate(LocalDateTime.of(2022, 12,5,12,00));
//        programDTO11.setClosingDate(LocalDateTime.of(2023, 12,16,12,00));
//        programDTO12.setProgramTime(3);
//        programDTO12.setProgramDate(LocalDateTime.of(2022,12,24,15,00));
//        programDTO12.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요" +
//                "* 해당 회차는 크리스마스 특별 프로그램입니다 *");
//        programDTO12.setProgramLimitCount(100);
//        programDTO12.setProgramStatus(ProgramStatus.마감);
//        programDTO12.setProgramFileProfileName("/images/program/program12.png");
//        programDTO12.setProgramFileProfilePath("/images/program/program12.png");
//        programDTO12.setProgramFileProfileUuid("/images/program/program12.png");
//        programDTO12.setProgramFileDetailName("/images/program/program12-1.png");
//        programDTO12.setProgramFileDetailPath("/images/program/program12-1.png");
//        programDTO12.setProgramFileDetailUuid("/images/program/program12-1.png");
//
//        Program program01 = programDTO01.toEntity();
//        Program program02 = programDTO02.toEntity();
//        Program program03 = programDTO03.toEntity();
//        Program program04 = programDTO04.toEntity();
//        Program program05 = programDTO05.toEntity();
//        Program program06 = programDTO06.toEntity();
//        Program program07 = programDTO07.toEntity();
//        Program program08 = programDTO08.toEntity();
//        Program program09 = programDTO09.toEntity();
//        Program program10 = programDTO10.toEntity();
//        Program program11 = programDTO11.toEntity();
//        Program program12 = programDTO12.toEntity();
//
//
//        programRepository.save(program01);
//        programRepository.save(program02);
//        programRepository.save(program03);
//        programRepository.save(program04);
//        programRepository.save(program05);
//        programRepository.save(program06);
//        programRepository.save(program07);
//        programRepository.save(program08);
//        programRepository.save(program09);
//        programRepository.save(program10);
//        programRepository.save(program11);
//        programRepository.save(program12);
//
//    }
//
//    @Test
//    public void findTest(){
//        Optional<Program> findProgram = programRepository.findById(11L);
//        if(findProgram.isPresent()){
//            Assertions.assertThat(findProgram.get().getProgramId().equals(11L));
//            log.info("11번 : "+findProgram.get().getProgramId().equals(11L));
//        }
//
//        Program program = findProgram.get();
//        log.info("ProgramStatus"+program.getProgramStatus());
//
//    }
//
//    @Test
//    public void updateTest(){
//        ProgramDTO programDTO = new ProgramDTO();
//        Program program= programRepository.findById(100L).get();
//        programDTO.setProgramStatus(ProgramStatus.마감);
//
//        programDTO.setProgramName("ZERO WASTE & 쓰담 SPECIAL");
//        programDTO.setProgramPlace("역삼쓰담빌딩 아트홀 ");
//        programDTO.setOpeningDate(LocalDateTime.of(2022, 12,5,12,00));
//        programDTO.setClosingDate(LocalDateTime.of(2022, 12,12,12,00));
//        programDTO.setProgramTime(3);
//        programDTO.setProgramDate(LocalDateTime.of(2022,12,24,15,00));
//        programDTO.setProgramContent("“ZERO WASTE의 정확한 이해” " +
//                "TEAM 개발도상국 X 쓰담학교" +
//                "자연과 생태를 이해하고 우리가 실천할 수 있는" +
//                "작은 것들을 이야기해봅니다" +
//                "지구를 위하는 마음을 담아 " +
//                "TEAM 개발도상국에서 쓰담학교를 지원합니다 " +
//                "ZERO WASTE 에 대한 정확한 의미와" +
//                "왜 쓰담수거, 별별마켓, 별별줍깅을 해야하는지" +
//                "함께 알아보는 시간을 가집니다" +
//                "무상프로그램으로 모두 함께 참여해보아요" +
//                "* 해당 회차는 크리스마스 특별 프로그램입니다 *");
//        programDTO.setProgramLimitCount(100);
//        programDTO.setProgramFileProfileName("/images/program/program12.png");
//        programDTO.setProgramFileProfilePath("/images/program/program12.png");
//        programDTO.setProgramFileProfileUuid("/images/program/program12.png");
//        programDTO.setProgramFileDetailName("/images/program/program12-1.png");
//        programDTO.setProgramFileDetailPath("/images/program/program12-1.png");
//        programDTO.setProgramFileDetailUuid("/images/program/program12-1.png");
//
//        program.update(programDTO);
//
//    }
//
//    /* 프로그램 검색 _ 프로그램 제목 _ 키워드 검색 */
//    @Test
//    public void searchProgramTest(){
//        programRepository.searchProgram("줍깅").stream().map(ProgramDTO::toString).forEach(log::info);
//    }
//
//    @Test
//    public void deleteTest(){
//        programRepository.deleteById(100L);
//    }

    @Test
    public void programDynamicListTest (){

        log.info("전체리스트 : "+programDynamicRepository.findAll().size());

        Pageable pageable = PageRequest.of(0,12); //0 페이지 12개 (1 페이지 12개면 13부터 24까지)
//
//        String keyword = null;
//        String programStatus = null;
//
//        Page<ProgramDTO> programs = programDynamicRepository.programDynamicList(programStatus, keyword, pageable);

        log.info("아아아아아 : "+ programDynamicRepository.programDynamicList("","",pageable).getSize());
//        Search search = new Search();
//        search.setKeyword(""); //검색은 빈문자열도 가능 (엔드로 되어있는건 안보임)
//        search.setProgramStatus("");
//        search.setProgramStatus(ProgramStatus.마감.name());
//        search.setProgramStatus(ProgramStatus.모집중.name());
//        search.setProgramStatus(ProgramStatus.모집완료);
//        search.setProgramStatus(ProgramStatus.마감);
//        log.info(" 검색어 , 상태 : " + programRepository.searchProgram("쓰담").size());
//        programDynamicRepository.programDynamicList("","");
    }

}