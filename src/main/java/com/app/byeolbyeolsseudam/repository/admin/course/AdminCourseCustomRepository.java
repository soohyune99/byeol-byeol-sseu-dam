package com.app.byeolbyeolsseudam.repository.admin.course;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;

import java.util.List;

public interface AdminCourseCustomRepository {
    public List<CourseDTO> showCourseList();
    public void update(CourseDTO courseDTO);
    public CourseDTO selectById(Long courseID);
}
