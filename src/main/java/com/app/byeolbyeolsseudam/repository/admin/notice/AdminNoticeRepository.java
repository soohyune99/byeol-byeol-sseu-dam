package com.app.byeolbyeolsseudam.repository.admin.notice;

import com.app.byeolbyeolsseudam.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminNoticeRepository extends JpaRepository<Notice, Long>, AdminNoticeCustomRepository {
}
