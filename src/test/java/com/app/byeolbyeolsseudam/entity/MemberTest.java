//package com.app.byeolbyeolsseudam.entity;
//
//import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
//import com.app.byeolbyeolsseudam.entity.member.Member;
//import com.app.byeolbyeolsseudam.entity.member.QMember;
//import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
//import com.app.byeolbyeolsseudam.service.login.MemberLoginService;
//import com.app.byeolbyeolsseudam.type.MemberCategory;
//import com.app.byeolbyeolsseudam.type.MemberLoginType;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.Optional;
//
//import static com.app.byeolbyeolsseudam.entity.member.QMember.*;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//@Slf4j
//@SpringBootTest
//@Transactional
//@Rollback(false)
//public class MemberTest {
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    JPAQueryFactory jpaQueryFactory;
//
//    @Autowired
//    MemberLoginService memberLoginService;
//
//    @Test
//    public void saveTest(){
//        MemberDTO memberDTO = new MemberDTO();
//
//        memberDTO.setMemberLoginType(MemberLoginType.네이버);
//        memberDTO.setMemberCategory(MemberCategory.일반회원);
//        memberDTO.setMemberName("장선홍");
//        memberDTO.setMemberEmail("jsh5060@dreamwiz.com");
//        memberDTO.setMemberPassword("1234");
//        memberDTO.setMemberAddress("서울강동");
//        memberDTO.setMemberPhone("01012345678");
//
//        memberRepository.save(memberDTO.toEntity());
//    }
//
//    @Test
//    public void findTest(){
//        assertThat(memberRepository.findByMemberEmail("jsh5060@dreamwiz.com").getMemberEmail()).isEqualTo("jsh5060@dreamwiz.com");
//    }
//
//    @Test
//    public void updateTest(){
//
//        MemberDTO memberDTO = new MemberDTO();
//        memberDTO.setMemberEmail("wkdtjsghd5@naver.com");
//        String tempPassword = memberLoginService.GetTempPassword();
//        memberLoginService.UpdatePassword(memberDTO, tempPassword);
//
////        memberLoginService.Send(memberDTO);
//
////        memberRepository.findByMemberEmail("wkdtjsghd5@naver.com").setMemberPassword("123467788");
//
////        MemberDTO memberDTO = new MemberDTO();
////        memberDTO.setMemberLoginType(MemberLoginType.네이버);
////        memberDTO.setMemberCategory(MemberCategory.기사회원);
////        memberDTO.setMemberName("장선홍");
////        memberDTO.setMemberEmail("dre4135@gmail.com");
////        memberDTO.setMemberPassword("1234");
////        memberDTO.setMemberAddress("서울강동");
////        memberDTO.setMemberPhone("01012345678");
////
////        member.update(memberDTO);
//
//    }
//
//
//
//
//    @Test
//    public void deleteTest(){
//        Optional<Member> deleteMember = memberRepository.findById(1L);
//
//        if(deleteMember.isPresent()){
//            memberRepository.delete(deleteMember.get());
//        }
//
//    }
//
//    @Test
//    public void findAllTest(){
//        memberRepository.findAll().stream().map(Member::toString).forEach(log::info);
//    }
//
//    @Test
//    public void findAllTest2(){
//        jpaQueryFactory.selectFrom(member).fetch().stream().map(Member::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void findByEmailTest(){
//        jpaQueryFactory.selectFrom(member).where(member.memberEmail.eq("jsh5060@dreamwiz.com"))
//                .orderBy(member.memberId.desc()).fetch()
//                .stream().map(Member::toString).forEach(log::info);
//    }
//
//    @Test
//    public void updateTest2(){
//        jpaQueryFactory.update(member).set(member.memberPhone, "01054214321").where(member.memberId.eq(3L)).execute();
//
//    }
//
//
//    @Test
//    public void deleteTest2(){
//        jpaQueryFactory.delete(member).where(member.memberId.eq(5L)).execute();
//    }
//
//
//
//
//
//
//
//
//}
