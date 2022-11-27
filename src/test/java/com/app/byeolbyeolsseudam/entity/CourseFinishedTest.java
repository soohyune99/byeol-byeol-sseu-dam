package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.repository.CourseFinishedRepository;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
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
public class CourseFinishedTest {
    @Autowired
    private CourseFinishedRepository courseFinishedRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){

    }
}
