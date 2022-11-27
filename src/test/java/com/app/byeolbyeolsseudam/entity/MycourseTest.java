package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MycourseDTO;
import com.app.byeolbyeolsseudam.repository.CourseRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MycourseRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MycourseTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private MycourseRepository mycourseRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MycourseDTO mycourseDTO = new MycourseDTO();

        mycourseDTO.setCourse(courseRepository.findAll().get(0));
        mycourseDTO.setMember(memberRepository.findAll().get(0));

        mycourseRepository.save(mycourseDTO.toEntity());
    }

    @Test
    public void findTest(){

    }

    @Test
    public void updateTest(){

    }

    @Test
    public void deleteTest(){

    }
}
