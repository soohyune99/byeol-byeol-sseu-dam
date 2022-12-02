package com.app.byeolbyeolsseudam.repository.notice;

import com.app.byeolbyeolsseudam.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeCustomRepository {
}
