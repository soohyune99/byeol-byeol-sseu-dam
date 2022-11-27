package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MycourseDTO;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MycourseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.byeolbyeolsseudam.entity.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.QMycourse.mycourse;
import static com.app.byeolbyeolsseudam.entity.QSpot.spot;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MycourseTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MycourseRepository mycourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MycourseDTO mycourseDTO = new MycourseDTO();

        mycourseDTO.setCourse(courseRepository.findAll().get(0));
        mycourseDTO.setMember(memberRepository.findAll().get(0));

        mycourseRepository.save(mycourseDTO.toEntity());
    }

    @Test
    public void findTest(){
        jpaQueryFactory.select(mycourse.mycourseId)
                .from(mycourse).orderBy(mycourse.mycourseId.desc())
                .fetch().stream().forEach(m -> log.info("Id : " + m));
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(mycourse)
                .orderBy(mycourse.mycourseId.desc())
                .limit(1)
                .fetchOne()
                .update(
                        jpaQueryFactory.selectFrom(course).limit(1).fetchOne(),
                        jpaQueryFactory.selectFrom(spot).limit(1).fetchOne()
                );
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(mycourse)
                .where(mycourse.spot.spotId.isNull())
                .execute();
    }
}
