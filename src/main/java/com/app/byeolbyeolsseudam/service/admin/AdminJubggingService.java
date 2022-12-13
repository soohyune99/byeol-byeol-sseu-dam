package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.repository.admin.badge.AdminBadgeRepository;
import com.app.byeolbyeolsseudam.repository.admin.course.AdminCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminJubggingService {
    private final AdminCourseRepository adminCourseRepository;
    private final AdminBadgeRepository adminBadgeRepository;

    public List<CourseDTO> showCourse(){
        return adminCourseRepository.showCourseList();
    }
    public void saveCourse(CourseDTO courseDTO){
        adminCourseRepository.save(courseDTO.toEntity());
    }

    public void removeCourse(List<String> courseIdstr){
        List<Long> courseId = new ArrayList<>();
        courseIdstr.stream().map(Long::parseLong).forEach(courseId::add);
        courseId.forEach(adminCourseRepository::deleteById);
    }

    public CourseDTO selectById(String courseIdstr){
        Long courseId = Long.parseLong(courseIdstr);
        return adminCourseRepository.selectById(courseId);
    }

    public void updateCourse(CourseDTO courseDTO, Long courseId){
        adminCourseRepository.update(courseDTO);
        Course course = adminCourseRepository.findById(courseId).get();
        adminCourseRepository.save(course);

    }
    public void saveBadge(BadgeDTO badgeDTO){adminBadgeRepository.save(badgeDTO.toEntity());}

}
