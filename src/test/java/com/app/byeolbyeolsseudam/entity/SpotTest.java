package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.SpotDTO;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.SpotRepository;
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
public class SpotTest {
    @Autowired
    private SpotRepository spotRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void saveTest(){
        SpotDTO spotDTO = new SpotDTO();

        spotDTO.setSpotName("1지점");
        spotDTO.setSpotNumber(2);
        spotDTO.setSpotAddress("서울 어쩌구 어쩌구동");
        spotDTO.setSpotFile("course.png");
        spotDTO.setCourse(courseRepository.findAll().get(0));

        spotRepository.save(spotDTO.toEntity());
    }
}
