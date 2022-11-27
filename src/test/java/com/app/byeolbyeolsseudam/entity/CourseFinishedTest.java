package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseFinishedDTO;
import com.app.byeolbyeolsseudam.repository.CourseFinishedRepository;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.QCourseFinished.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CourseFinishedTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private CourseFinishedRepository courseFinishedRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        CourseFinishedDTO courseFinishedDTO = new CourseFinishedDTO();

        courseFinishedDTO.setCourseFinishedStatus(CourseFinishedStatus.진행중);
        courseFinishedDTO.setMember(memberRepository.findAll().get(0));
        courseFinishedDTO.setCourse(courseRepository.findAll().get(0));

        courseFinishedRepository.save(courseFinishedDTO.toEntity());
    }

    @Test
    public void findTest(){
        List<CourseFinished> courseFinisheds = jpaQueryFactory.selectFrom(courseFinished)
                .where(courseFinished.courseFinishedStatus.eq(CourseFinishedStatus.진행중))
                .fetch();

        courseFinisheds.stream().map(CourseFinished::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
        CourseFinished courseFinished =
                jpaQueryFactory.selectFrom(QCourseFinished.courseFinished)
                .orderBy(QCourseFinished.courseFinished.courseFinishedId.desc())
                .limit(1)
                .fetchOne();

        courseFinished.update(
                CourseFinishedStatus.미완주,
                jpaQueryFactory.selectFrom(course).orderBy(course.courseId.desc())
                        .limit(1).fetchOne()
        );
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(courseFinished)
                .where(courseFinished.courseFinishedStatus.eq(CourseFinishedStatus.미완주))
                .execute();
    }
}
