package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseDTO;
import com.app.byeolbyeolsseudam.domain.SpotDTO;
import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.SpotRepository;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class SpotTest {
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveTest(){
        SpotDTO spotDTO = new SpotDTO();
        CourseDTO courseDTO = new CourseDTO();
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setClosingDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        possibleDate.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));

        courseDTO.setCourseName("A코스");
        courseDTO.setCourseArea("강남구");
        courseDTO.setCourseGrade(CourseGrade.초급);
        courseDTO.setCourseTime("1시간");
        courseDTO.setCourseDistance("3km");
        courseDTO.setCourseStart("역삼역 3번 출구");
        courseDTO.setCourseFinish("할리스 역삼스타점");
        courseDTO.setPossibleDate(possibleDate);
//        courseDTO.setOpeningDate(LocalDateTime.of(2022, 1, 1, 0, 0, 0));
//        courseDTO.setClosingDate(LocalDateTime.of(2022, 1, 25, 0, 0,0));

        courseRepository.save(courseDTO.toEntity());

        spotDTO.setSpotName("1지점");
        spotDTO.setSpotNumber(2);
        spotDTO.setSpotAddress("서울 어쩌구 어쩌구동");
        spotDTO.setSpotFile("course.png");
        spotDTO.setCourse(courseRepository.findAll().get(0));

        spotRepository.save(spotDTO.toEntity());
    }
}
