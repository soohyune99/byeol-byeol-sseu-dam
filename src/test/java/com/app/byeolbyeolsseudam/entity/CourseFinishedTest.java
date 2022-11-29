package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseFinishedDTO;
import com.app.byeolbyeolsseudam.repository.CourseFinishedRepository;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MycourseRepository;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
    private MycourseRepository mycourseRepository;

    @Test
    public void saveTest(){
        CourseFinishedDTO courseFinishedDTO = new CourseFinishedDTO();

        courseFinishedDTO.setCourseFinishedStatus(CourseFinishedStatus.진행중);

        CourseFinished courseFinished = courseFinishedDTO.toEntity();
        courseFinishedRepository.save(courseFinished);
        courseFinished.changeMycourse(mycourseRepository.findAll().get(0));
    }

    @Test
    public void findTest(){
        jpaQueryFactory.selectFrom(courseFinished)
                .where(courseFinished.courseFinishedStatus.eq(CourseFinishedStatus.진행중))
                .fetch()
                .stream().map(CourseFinished::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(QCourseFinished.courseFinished)
                .orderBy(QCourseFinished.courseFinished.courseFinishedId.desc())
                .limit(1)
                .fetchOne()
                .update(CourseFinishedStatus.미완주);
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(courseFinished)
                .where(courseFinished.courseFinishedStatus.eq(CourseFinishedStatus.미완주))
                .execute();
    }
}
