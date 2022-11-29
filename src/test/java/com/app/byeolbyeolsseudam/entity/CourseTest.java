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
        courseDTO.setCourseFileName("A-course.png");
        courseDTO.setCourseFilePath("/upload");
        courseDTO.setCourseFileUuid("course");
        courseDTO.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        courseDTO.setClosingDate(LocalDateTime.of(2022, 1, 25, 0, 0,0));

        Course course = courseDTO.toEntity();

        courseRepository.save(course);
//        course.getSpots().stream().forEach(spot -> spot.changeCourse(course));
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

        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setClosingDate(LocalDateTime.of(2022,11,29,0,0,0));
        possibleDate.setOpeningDate(LocalDateTime.of(2022,11,29,0,0,0));

        jpaQueryFactory.selectFrom(course)
                .where(course.courseId.eq(87L))
//                .orderBy(course.courseId.)
                .limit(1)
                .fetchOne()
                .update("수정된 코스", "관악구", "9km",
                        "2시간", CourseGrade.중급, "보라매공원 앞",
                        "서울대입구", "boramae.png", "/upload",
                        "updateCourse", possibleDate);
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(course)
                .where(course.courseFileName.isNull())
                .execute();
    }
}
