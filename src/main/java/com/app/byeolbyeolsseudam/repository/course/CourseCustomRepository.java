package com.app.byeolbyeolsseudam.repository.course;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;

import java.util.List;

public interface CourseCustomRepository {
    public List<CourseDTO> showCourseList();
    public CourseDTO showCourse(int courseNumber);
    public CourseDTO showSpecialCourse();
}
