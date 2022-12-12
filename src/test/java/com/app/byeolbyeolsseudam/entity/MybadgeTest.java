package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.entity.mybadge.Mybadge;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.byeolbyeolsseudam.entity.mybadge.QMybadge.mybadge;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MybadgeTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MybadgeRepository mybadgeRepository;
    @Autowired
    private BadgeRepository badgeRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        Mybadge mybadge = new Mybadge();

        mybadge.changeBadge(badgeRepository.findAll().get(8));
        mybadge.changeMember(memberRepository.findById(1L).get());

        mybadgeRepository.save(mybadge);
    }

    @Test
    public void findTest(){
        jpaQueryFactory.selectFrom(mybadge)
                .orderBy(mybadge.mybadgeId.desc())
                .fetch().stream().map(Mybadge::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(mybadge)
                .orderBy(mybadge.mybadgeId.desc())
                .limit(1)
                .fetchOne();
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(mybadge)
                .where(mybadge.badge.badgeId.eq(5L))
                .execute();
    }
}
