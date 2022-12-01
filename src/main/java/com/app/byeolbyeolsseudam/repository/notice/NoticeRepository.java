package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
