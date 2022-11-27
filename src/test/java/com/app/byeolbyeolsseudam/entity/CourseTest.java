package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseDTO;
import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.SpotRepository;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.byeolbyeolsseudam.entity.QCourse.*;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CourseTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SpotRepository spotRepository;

    @Test
    public void saveTest(){
        CourseDTO courseDTO = new CourseDTO();
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        possibleDate.setClosingDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));

        courseDTO.setCourseName("A코스");
        courseDTO.setCourseArea("강남구");
        courseDTO.setCourseGrade(CourseGrade.초급);
        courseDTO.setCourseTime("1시간");
        courseDTO.setCourseDistance("3km");
        courseDTO.setCourseStart("역삼역 3번 출구");
        courseDTO.setCourseFinish("할리스 역삼스타점");
        courseDTO.setSpots(spotRepository.findAll());
        courseDTO.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        courseDTO.setClosingDate(LocalDateTime.of(2022, 1, 25, 0, 0,0));

        courseRepository.save(courseDTO.toEntity());
    }

    @Test
    public void findTest(){
        List<Course> courses = jpaQueryFactory.selectFrom(course)
                .where(course.courseArea.eq("강남구"))
                .orderBy(course.courseId.desc())
                .fetch();

        courses.stream().map(Course::toString).forEach(log::info);
    }

    @Test
    public void updateTest(){
//        log.info("course : " + jpaQueryFactory.select(course.courseName, course.courseArea).from(course)
//                .where(course.courseArea.eq("강남구"))
//                .orderBy(course.courseId.desc())
//                .fetch());

        Course course = jpaQueryFactory.selectFrom(QCourse.course)
                .where(QCourse.course.courseArea.eq("강남구"))
                .orderBy(QCourse.course.courseId.desc())
                .limit(1)
                .fetchOne();

        course.update("수정된 코스", "관악구", "update.png", "10km",
                "4시간", CourseGrade.고급, "서울숲", "성수역 노티스",
                new PossibleDate());

        log.info(course.toString());
    }

    @Test
    public void deleteTest(){
        long result = jpaQueryFactory.delete(course)
                .where(course.courseId.eq(39L))
                .execute();

        log.info("삭제 수 : " + result);
    }
}
