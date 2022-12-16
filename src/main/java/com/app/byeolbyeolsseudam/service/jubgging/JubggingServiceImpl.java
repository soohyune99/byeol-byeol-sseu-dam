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
import java.util.Optional;
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

        Mycourse mycourse = mycourseRepository.findActiveCourse(memberDTO, courseName);
        MycourseDTO mycourseDTO = null;

        if(mycourse != null){
            mycourse.changeSpot(spot);
            mycourse.updateStatus(
                    course.getSpots().size() == spotNumber ?
                            CourseFinishedStatus.완주 : CourseFinishedStatus.진행중
            );
            mycourseRepository.save(mycourse);

        } else {
            mycourseDTO = new MycourseDTO();
            mycourseDTO.setCourseFinishedStatus(
                    course.getSpots().size() == spotNumber? CourseFinishedStatus.완주 : CourseFinishedStatus.진행중
            );
            mycourse = mycourseDTO.toEntity();
            mycourse.changeMember(member);
            mycourse.changeCourse(course);
            mycourse.changeSpot(spot);
            mycourseRepository.save(mycourse);
        }

        // 방금 저장 혹은 수정한 mycourse 불러오기
        mycourseDTO = mycourseRepository.selectMyCourse(memberDTO.getMemberId());

        giveJubggingBadge(mycourseDTO);

        return mycourseDTO;
    }

    /* 달성 조건 만족 시 배지 지급 */
    public void giveJubggingBadge(MycourseDTO mycourseDTO){
        IntStream.rangeClosed(1, 5).forEach( i -> {
            if(mycourseRepository.badgeCondition(mycourseDTO.getMemberId(), i + "코스") > 0){
                Mybadge mybadge = new Mybadge();
                mybadge.changeMember(memberRepository.findById(mycourseDTO.getMemberId()).get());
                mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining("줍깅 " + i + "코스"));
                mybadgeRepository.save(mybadge);
            }
        });

        if(mycourseRepository.badgeCondition(mycourseDTO.getMemberId(), "SPECIAL") > 0){
            Mybadge mybadge = new Mybadge();
            mybadge.changeMember(memberRepository.findById(mycourseDTO.getMemberId()).get());
            mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining("스페셜"));
            mybadgeRepository.save(mybadge);
        }

        for(int i = 1; i <= 6; i++){
            if(mycourseRepository.badgeCondition(mycourseDTO.getMemberId(), null) == i * 5){
                Mybadge mybadge = new Mybadge();
                mybadge.changeMember(memberRepository.findById(mycourseDTO.getMemberId()).get());
                mybadge.changeBadge(badgeRepository.findByBadgeInfoContaining((i * 5) + "회"));
                mybadgeRepository.save(mybadge);
            }
        }
    }
}
