//package com.app.byeolbyeolsseudam.entity;
//
//import com.app.byeolbyeolsseudam.domain.MypointDTO;
//import com.app.byeolbyeolsseudam.repository.MemberRepository;
//import com.app.byeolbyeolsseudam.repository.MypointRepository;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static com.app.byeolbyeolsseudam.entity.QMember.member;
//import static com.app.byeolbyeolsseudam.entity.QMypoint.mypoint;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class MypointTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private MypointRepository mypointRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void saveTest(){
//        for(int i = 0; i < 10; i++){
//            MypointDTO mypointDTO = new MypointDTO();
//
//            mypointDTO.setMypointContent("신규회원가입");
//            mypointDTO.setMypointInout(3000);
//
//            Mypoint mypoint = mypointDTO.toEntity();
//            mypointRepository.save(mypoint);
//            mypoint.changeMember(memberRepository.findAll().get(0));
//        }
//    }
//
//    @Test
//    public void findTest(){
//        jpaQueryFactory.select(mypoint.mypointInout).from(mypoint)
//                .orderBy(mypoint.mypointId.asc())
//                .fetch().stream().forEach(m -> log.info("point : " + m));
//    }
//
//    @Test
//    public void updateTest(){
//        jpaQueryFactory.selectFrom(mypoint)
//                .orderBy(mypoint.mypointId.desc())
//                .fetchAll()
//                .stream().forEach(m -> m.changeMember(
//                        jpaQueryFactory.selectFrom(member)
//                                .orderBy(member.memberId.desc())
//                                .limit(1)
//                                .fetchOne()
//        ));
//    }
//
//    @Test
//    public void deleteTest(){
//        jpaQueryFactory.delete(mypoint)
//                .where(mypoint.mypointId.eq(3L))
//                .execute();
//    }
//
//}
