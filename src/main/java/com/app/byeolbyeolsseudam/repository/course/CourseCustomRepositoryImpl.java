package com.app.byeolbyeolsseudam.repository.course;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.domain.spot.QSpotDTO;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.spot.QSpot;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.spot.QSpot.spot;


@Repository
@RequiredArgsConstructor
public class CourseCustomRepositoryImpl implements CourseCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CourseDTO> showCourseList(){
        List<CourseDTO> courses = jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName, course.courseArea, course.courseDistance,
                course.courseTime, course.courseStart, course.courseFinish, course.courseGrade,
                course.courseFileName, course.courseFilePath, course.courseFileUuid,
                course.possibleDate.openingDate, course.possibleDate.closingDate))
                .from(course)
                .where(course.courseGrade.ne(CourseGrade.스페셜))
                .orderBy(course.courseId.desc())
                .limit(5)
                .fetch();
        courses.add(jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName, course.courseArea, course.courseDistance,
                course.courseTime, course.courseStart, course.courseFinish, course.courseGrade,
                course.courseFileName, course.courseFilePath, course.courseFileUuid,
                course.possibleDate.openingDate, course.possibleDate.closingDate))
                .from(course)
                .where(course.courseGrade.eq(CourseGrade.스페셜))
                .limit(1)
                .fetchOne());
        return courses;
    }

    @Override
    public CourseDTO showCourse(int courseNumber){
        CourseDTO courseDTO = jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName, course.courseArea, course.courseDistance,
                course.courseTime, course.courseStart, course.courseFinish, course.courseGrade,
                course.courseFileName, course.courseFilePath, course.courseFileUuid,
                course.possibleDate.openingDate, course.possibleDate.closingDate))
                .from(course)
                .where(course.courseName.contains(courseNumber + "코스"))
                .orderBy(course.createdDate.desc())
                .limit(1)
                .fetchOne();
        courseDTO.setSpots(jpaQueryFactory.select(new QSpotDTO(
                spot.spotId, spot.spotName, spot.spotAddress, spot.spotNumber, spot.spotQrName,
                spot.spotQrPath, spot.spotQrUuid))
                .from(spot)
                .where(spot.course.courseId.eq(courseDTO.getCourseId()))
                .fetch());
        return courseDTO;
    }

    @Override
    public CourseDTO showSpecialCourse(){
        CourseDTO courseDTO = jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName, course.courseArea, course.courseDistance,
                course.courseTime, course.courseStart, course.courseFinish, course.courseGrade,
                course.courseFileName, course.courseFilePath, course.courseFileUuid,
                course.possibleDate.openingDate, course.possibleDate.closingDate))
                .from(course)
                .where(course.courseGrade.eq(CourseGrade.스페셜))
                .orderBy(course.createdDate.desc())
                .limit(1)
                .fetchOne();
        courseDTO.setSpots(jpaQueryFactory.select(new QSpotDTO(
                spot.spotId, spot.spotName, spot.spotAddress, spot.spotNumber, spot.spotQrName,
                spot.spotQrPath, spot.spotQrUuid))
                .from(spot)
                .where(spot.course.courseId.eq(courseDTO.getCourseId()))
                .fetch());
        return courseDTO;
    }

}
