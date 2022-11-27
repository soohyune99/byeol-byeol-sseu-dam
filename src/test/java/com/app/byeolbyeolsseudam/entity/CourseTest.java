package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseDTO;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.SpotRepository;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CourseTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SpotRepository spotRepository;

    @Test
    public void saveTest(){
        CourseDTO courseDTO = new CourseDTO();

        courseDTO.setCourseName("A코스");
        courseDTO.setCourseArea("강남구");
        courseDTO.setCourseGrade(CourseGrade.초급);
        courseDTO.setCourseTime("1시간");
        courseDTO.setCourseDistance("3km");
        courseDTO.setCourseStart("역삼역 3번 출구");
        courseDTO.setCourseFinish("할리스 역삼스타점");
//        courseDTO.setSpots(spotRepository.findById());



    }
}
