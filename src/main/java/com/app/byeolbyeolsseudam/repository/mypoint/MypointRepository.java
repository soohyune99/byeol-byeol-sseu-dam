package com.app.byeolbyeolsseudam.repository.mypoint;

import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypointRepository extends JpaRepository<Mypoint, Long>, MypointCustomRepository {
}
