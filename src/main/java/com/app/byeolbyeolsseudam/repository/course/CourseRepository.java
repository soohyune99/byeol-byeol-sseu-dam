package com.app.byeolbyeolsseudam.repository.course;

import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseCustomRepository {
    public Course findByCourseNameContains(String courseName);
}
