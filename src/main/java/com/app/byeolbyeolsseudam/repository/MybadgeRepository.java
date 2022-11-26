package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Mybadge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MybadgeRepository extends JpaRepository<Mybadge, Long> {
}
