package com.app.byeolbyeolsseudam.repository.mycourse;

import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MycourseRepository extends JpaRepository<Mycourse, Long>, MycourseCustomRepository {
}
