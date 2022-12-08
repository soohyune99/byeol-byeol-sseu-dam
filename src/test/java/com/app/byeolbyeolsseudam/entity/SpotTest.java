package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.repository.course.CourseRepository;
import com.app.byeolbyeolsseudam.repository.spot.SpotRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.app.byeolbyeolsseudam.entity.spot.QSpot.spot;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SpotTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveTest(){
        SpotDTO spotDTO = new SpotDTO();
//        CourseDTO courseDTO = new CourseDTO();
//        PossibleDate possibleDate = new PossibleDate();
//        possibleDate.setClosingDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
//        possibleDate.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
//
//        courseDTO.setCourseName("A코스");
//        courseDTO.setCourseArea("강남구");
//        courseDTO.setCourseGrade(CourseGrade.초급);
//        courseDTO.setCourseTime("1시간");
//        courseDTO.setCourseDistance("3km");
//        courseDTO.setCourseStart("역삼역 3번 출구");
//        courseDTO.setCourseFinish("할리스 역삼스타점");
//        courseDTO.setCourseFileName("A-course.png");
//        courseDTO.setCourseFilePath("/upload");
//        courseDTO.setCourseFileUuid("course");
//        courseDTO.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
//        courseDTO.setClosingDate(LocalDateTime.of(2022, 1, 25, 0, 0,0));
//
//        Course course = courseDTO.toEntity();
//
//        courseRepository.save(course);

        for (int i = 0; i < 5; i++) {
            Course course = jpaQueryFactory.selectFrom(QCourse.course)
                    .orderBy(QCourse.course.courseId.desc())
                    .limit(1)
                    .fetchOne();

            spotDTO.setSpotName("1코스 " + (i + 1) + "지점");
            spotDTO.setSpotNumber(i + 1);
            spotDTO.setSpotAddress("서울 블라구 블라블라동");
            spotDTO.setSpotQrName("course.png");
            spotDTO.setSpotQrPath("/upload");
            spotDTO.setSpotQrUuid("qrqrqrqr");

            Spot spot = spotDTO.toEntity();

            spotRepository.save(spot);
            spot.changeCourse(courseRepository.findById(2L).get());
        }
    }

    @Test
    public void findTest(){
        log.info("Spot : " + jpaQueryFactory.select(spot.spotAddress)
                                .from(spot)
                                .where(spot.spotName.eq("1지점"))
                                .limit(1)
                                .fetchOne());
    }

    @Test
    public void updateTest(){
        SpotDTO spotDTO = new SpotDTO();
        spotDTO.setSpotName("수정된 지점");

        jpaQueryFactory.selectFrom(spot)
                .orderBy(spot.spotId.desc())
                .limit(1)
                .fetchOne()
                .update(spotDTO);
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(spot)
                .where(spot.spotId.eq(20L))
                .execute();
    }

}
