package com.app.byeolbyeolsseudam.repository.admin.course;

import com.app.byeolbyeolsseudam.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminCourseRepository extends JpaRepository<Course, Long>, AdminCourseCustomRepository {

    public Course findByCourseNameContains(String courseName);
}
