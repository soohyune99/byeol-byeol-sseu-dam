package com.app.byeolbyeolsseudam.service.jubgging;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.badge.QBadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.course.QCourseDTO;
import com.app.byeolbyeolsseudam.entity.course.QCourse;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.course.CourseRepository;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.badge.QBadge.badge;
import static com.app.byeolbyeolsseudam.entity.course.QCourse.course;

@Service
@RequiredArgsConstructor
public class JubggingServiceImpl implements JubggingService {
    private final BadgeRepository badgeRepository;
    private final CourseRepository courseRepository;

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

}
