package com.app.byeolbyeolsseudam.service.jubgging;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.badge.QBadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.mybadge.Mybadge;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.course.CourseRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.app.byeolbyeolsseudam.repository.mycourse.MycourseRepository;
import com.app.byeolbyeolsseudam.repository.spot.SpotRepository;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static com.app.byeolbyeolsseudam.entity.badge.QBadge.badge;
import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;

@Service
@Slf4j
@RequiredArgsConstructor
public class JubggingServiceImpl implements JubggingService {
    private final BadgeRepository badgeRepository;
    private final CourseRepository courseRepository;
    private final SpotRepository spotRepository;
    private final MycourseRepository mycourseRepository;
    private final MemberRepository memberRepository;
    private final MybadgeRepository mybadgeRepository;

    @Override
    public List<BadgeDTO> getBadgeList(){
        return badgeRepository.showBadgeList();
    }

    @Override
    public List<CourseDTO> getCourseList(){
        return courseRepository.showCourseList();
    }

    @Override
    public CourseDTO showCourse(int courseNumber){
        return courseRepository.showCourse(courseNumber);
    }

    @Override
    public CourseDTO showSpecialCourse(){
        return courseRepository.showSpecialCourse();
    }

    @Override
    public MycourseDTO insertMycourse(MemberDTO memberDTO, String courseName, int spotNumber){
        Member member = memberRepository.findById(memberDTO.getMemberId()).get();
        Course course = courseRepository.findByCourseNameContains(courseName);
        Spot spot = spotRepository.findBySpotNumberAndCourseCourseName(spotNumber, course.getCourseName());

        MycourseDTO mycourseDTO = new MycourseDTO();
        mycourseDTO.setCourseFinishedStatus(
                course.getSpots().size() == spotNumber? CourseFinishedStatus.완주 : CourseFinishedStatus.진행중
        );
        Mycourse mycourse = mycourseDTO.toEntity();
        mycourse.changeMember(member);
        mycourse.changeCourse(course);
        mycourse.changeSpot(spot);
        mycourseRepository.save(mycourse);

        giveJubggingBadge(memberDTO.getMemberId());

        mycourseDTO = mycourseRepository.selectMyCourse(memberDTO.getMemberId());

        return mycourseDTO;
    }

    /* 달성 조건 만족 시 배지 지급 */
    public void giveJubggingBadge(Long memberId){
        IntStream.rangeClosed(1, 5).forEach( i -> {
            if(mycourseRepository.badgeCondition(memberId, i + "코스") > 0){
                Mybadge mybadge = new Mybadge();
                mybadge.changeMember(memberRepository.findById(memberId).get());
                mybadgeRepository.save(mybadge);
                mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining(i + "코스"));
            }
        });

        if(mycourseRepository.badgeCondition(memberId, "SPECIAL") > 0){
            Mybadge mybadge = new Mybadge();
            mybadge.changeMember(memberRepository.findById(memberId).get());
            mybadgeRepository.save(mybadge);
            mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining("SPECIAL"));
        }

        for(int i = 1; i <= 6; i++){
            if(mycourseRepository.badgeCondition(memberId, null) == i * 5){
                Mybadge mybadge = new Mybadge();
                mybadge.changeMember(memberRepository.findById(memberId).get());
                mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining((i * 5) + "회"));
                mybadgeRepository.save(mybadge);
            }
        }
    }
}
