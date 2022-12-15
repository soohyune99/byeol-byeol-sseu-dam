package com.app.byeolbyeolsseudam.service.jubgging;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JubggingService {
    public List<BadgeDTO> getBadgeList();
    public List<CourseDTO> getCourseList();
    public CourseDTO showCourse(int courseNumber);
    public CourseDTO showSpecialCourse();
    public MycourseDTO insertMycourse(MemberDTO memberDTO, String courseName, int spotNumber);
}
