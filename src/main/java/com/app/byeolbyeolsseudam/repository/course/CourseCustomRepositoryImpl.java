package com.app.byeolbyeolsseudam.repository.course;

import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.QMycourseDTO;
import com.app.byeolbyeolsseudam.domain.spot.QSpotDTO;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.mycourse.QMycourse;
import com.app.byeolbyeolsseudam.entity.spot.QSpot;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;
import static com.app.byeolbyeolsseudam.entity.mycourse.QMycourse.mycourse;
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
                .orderBy(spot.spotNumber.asc())
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
                .orderBy(spot.spotNumber.asc())
                .fetch());
        return courseDTO;
    }

    @Override
    public List<CourseDTO> selectMyCourseList(Long memberId){
        List<CourseDTO> courses = jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName))
                .from(course)
                .orderBy(course.courseName.asc())
                .limit(6)
                .fetch();

        courses.stream().forEach(course -> {
            course.setSpots(
                    jpaQueryFactory.select(new QSpotDTO(
                            spot.spotId, spot.spotName, spot.spotAddress, spot.spotNumber, spot.course.courseId))
                            .from(spot)
                            .where(spot.course.courseId.eq(course.getCourseId()))
                            .orderBy(spot.spotNumber.asc())
                            .fetch()
            );
            course.setMycourses(
                    jpaQueryFactory.select(new QMycourseDTO(
                            mycourse.mycourseId, mycourse.courseFinishedStatus, mycourse.member.memberId,
                            mycourse.course.courseId, mycourse.course.courseName, mycourse.spot.spotId,
                            mycourse.spot.spotName, mycourse.spot.spotNumber, mycourse.createdDate))
                    .from(mycourse)
                    .where(mycourse.member.memberId.eq(memberId)
                            .and(mycourse.courseFinishedStatus.ne(CourseFinishedStatus.미완주)))
                    .orderBy(mycourse.course.courseName.asc())
                    .fetch()
            );
        });
        return courses;
    }

    @Override
    public CourseDTO selectCourse(Long courseId){
        CourseDTO courseDTO = jpaQueryFactory.select(new QCourseDTO(
                course.courseId, course.courseName))
                .from(course)
                .where(course.courseId.eq(courseId))
                .fetchOne();

        courseDTO.setSpots(
                jpaQueryFactory.select(new QSpotDTO(
                        spot.spotId, spot.spotName, spot.spotAddress, spot.spotNumber, spot.course.courseId))
                        .from(spot)
                        .where(spot.course.courseId.eq(courseId))
                        .fetch()
        );
        return courseDTO;
    }

}
