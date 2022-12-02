//package com.app.byeolbyeolsseudam.entity;
//
//import com.app.byeolbyeolsseudam.entity.course.Course;
//import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
//import com.app.byeolbyeolsseudam.repository.course.CourseRepository;
//import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
//import com.app.byeolbyeolsseudam.repository.mycourse.MycourseRepository;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class MycourseTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private MycourseRepository mycourseRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Test
//    public void saveTest(){
//        Mycourse mycourse = new Mycourse();
//
//        Course course = courseRepository.findById(17l).get();
//
//        mycourse.changeCourse(course);
//        mycourse.changeMember(memberRepository.findAll().get(0));
//        mycourse.changeSpot(course.getSpots().get(0));
//
//        mycourseRepository.save(mycourse);
//    }
//
//    @Test
//    public void findTest(){
//        jpaQueryFactory.select(mycourse.mycourseId)
//                .from(mycourse).orderBy(mycourse.mycourseId.desc())
//                .fetch().stream().forEach(m -> log.info("Id : " + m));
//
//        log.info("myCourse : " + jpaQueryFactory.select(mycourse.member.memberName, mycourse.course.courseName, mycourse.spot.spotNumber)
//                .from(mycourse)
//                .fetch());
//    }
//
//    @Test
//    public void updateTest(){
//        jpaQueryFactory.select(mycourse.member.memberName, mycourse.course.courseName, mycourse.spot.spotNumber)
//                .from(mycourse)
//                .fetch()
//                .stream().forEach(c -> log.info("myCourse : " + c));
//
//    }
//
//    @Test
//    public void deleteTest(){
//        jpaQueryFactory.delete(mycourse)
//                .where(mycourse.spot.spotId.isNull())
//                .execute();
//    }
//}
