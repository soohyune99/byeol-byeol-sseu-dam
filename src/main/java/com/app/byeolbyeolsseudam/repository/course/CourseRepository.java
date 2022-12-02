package com.app.byeolbyeolsseudam.repository.course;

import com.app.byeolbyeolsseudam.entity.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>, CourseCustomRepository {
}
