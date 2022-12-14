package com.app.byeolbyeolsseudam.service.jubgging;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.badge.QBadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.course.CourseRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mycourse.MycourseRepository;
import com.app.byeolbyeolsseudam.repository.spot.SpotRepository;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public MycourseDTO insertMycourse(Long memberId, String courseName, int spotNumber){
        Member member = memberRepository.findById(memberId).get();
        Course course = courseRepository.findByCourseNameContains(courseName);
        Spot spot = spotRepository.findBySpotNumber(spotNumber);

        MycourseDTO mycourseDTO = new MycourseDTO();
        mycourseDTO.setCourseFinishedStatus(
                course.getSpots().size() == spotNumber? CourseFinishedStatus.완주 : CourseFinishedStatus.진행중
        );
        Mycourse mycourse = mycourseDTO.toEntity();
        mycourse.changeMember(member);
        mycourse.changeCourse(course);
        mycourse.changeSpot(spot);
        mycourseRepository.save(mycourse);

        log.info("dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd" + mycourse.getMycourseId());

        return null;
    }
}
