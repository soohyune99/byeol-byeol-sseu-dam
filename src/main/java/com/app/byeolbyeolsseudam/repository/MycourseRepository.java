package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Mycourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MycourseRepository extends JpaRepository<Mycourse, Long> {
}
