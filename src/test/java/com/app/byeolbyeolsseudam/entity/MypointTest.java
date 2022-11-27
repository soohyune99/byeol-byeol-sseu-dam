package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MypointDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MypointRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.byeolbyeolsseudam.entity.QMypoint.mypoint;

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

        mypointDTO.setMypointContent("신규회원가입");
        mypointDTO.setMypointInout(3000);
        mypointDTO.setMember(memberRepository.findAll().get(0));

        mypointRepository.save(mypointDTO.toEntity());
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
                .stream().forEach(m -> m.update(
                    "포인트 수정",
                    1250
                ));
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(mypoint)
                .where(mypoint.mypointInout.eq(3000))
                .execute();
    }

}
