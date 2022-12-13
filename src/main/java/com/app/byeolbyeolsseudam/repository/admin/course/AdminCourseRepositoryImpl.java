package com.app.byeolbyeolsseudam.repository.admin.course;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;

@Repository
@RequiredArgsConstructor
public class AdminCourseRepositoryImpl implements AdminCourseCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<CourseDTO> showCourseList() {
        return jpaQueryFactory.select(new QCourseDTO(
                course.courseId,
                course.courseName,
                course.courseArea,
                course.courseDistance,
                course.courseTime,
                course.courseStart,
                course.courseFinish,
                course.courseGrade,
                course.courseFileName,
                course.courseFilePath,
                course.courseFileUuid,
                course.possibleDate.openingDate,
                course.possibleDate.closingDate
        )).from(course)
                .orderBy(course.courseName.asc())
                .limit(6)
                .fetch();
    }

    @Override
    public void update(CourseDTO courseDTO) {
        jpaQueryFactory.selectFrom(course)
                .where(course.courseId.eq(courseDTO.getCourseId()))
                .fetchOne().update(courseDTO);
    }

    @Override
    public CourseDTO selectById(Long courseID) {
        return jpaQueryFactory.select(new QCourseDTO(
                course.courseId,
                course.courseName,
                course.courseArea,
                course.courseDistance,
                course.courseTime,
                course.courseStart,
                course.courseFinish,
                course.courseGrade,
                course.courseFileName,
                course.courseFilePath,
                course.courseFileUuid,
                course.possibleDate.openingDate,
                course.possibleDate.closingDate
        )).from(course)
                .where(course.courseId.eq(courseID))
                .fetchOne();
    }
}
