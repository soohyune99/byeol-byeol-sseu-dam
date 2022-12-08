package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;
import static com.app.byeolbyeolsseudam.entity.mypoint.QMypoint.mypoint;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MypointTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MypointRepository mypointRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MypointDTO mypointDTO = new MypointDTO();

        mypointDTO.setMypointContent("신규 회원 가입");
        mypointDTO.setMypointInout(3000);

        Mypoint mypoint = mypointDTO.toEntity();
        mypointRepository.save(mypoint);
        mypoint.changeMember(memberRepository.findById(1L).get());

        for(int i = 0; i < 3; i++){
            MypointDTO mypointDTO2 = new MypointDTO();

            mypointDTO2.setMypointContent("줍깅 " + (i + 1) + "코스 참여");
            mypointDTO2.setMypointInout((i + 1) * 250);

            Mypoint mypoint2 = mypointDTO2.toEntity();
            mypointRepository.save(mypoint2);
            mypoint2.changeMember(memberRepository.findById(1L).get());
        }
    }

    @Test
    public void findTest(){
        jpaQueryFactory.select(mypoint.mypointInout).from(mypoint)
                .orderBy(mypoint.mypointId.asc())
                .fetch().stream().forEach(m -> log.info("point : " + m));
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(mypoint)
                .orderBy(mypoint.mypointId.desc())
                .fetchAll()
                .stream().forEach(m -> m.changeMember(
                        jpaQueryFactory.selectFrom(member)
                                .orderBy(member.memberId.desc())
                                .limit(1)
                                .fetchOne()
        ));
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(mypoint)
                .where(mypoint.mypointId.eq(3L))
                .execute();
    }

}
