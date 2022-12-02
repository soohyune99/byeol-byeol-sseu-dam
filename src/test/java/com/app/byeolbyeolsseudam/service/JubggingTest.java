package com.app.byeolbyeolsseudam.service;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.service.jubgging.JubggingService;
import com.app.byeolbyeolsseudam.service.jubgging.JubggingServiceImpl;
import lombok.RequiredArgsConstructor;
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
public class JubggingTest {
    @Autowired
    private JubggingService jubggingService;

    @Test
    public void findAllTest(){
        jubggingService.getBadgeList().stream().map(BadgeDTO::getBadgeName).forEach(log::info);
        jubggingService.getCourseList().stream().map(CourseDTO::getCourseName).forEach(log::info);
    }

}
