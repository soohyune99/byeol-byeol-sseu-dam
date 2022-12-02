package com.app.byeolbyeolsseudam.repository.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NoticeCustomRepository {

    // 공지사항 중요 조회
    public List<NoticeDTO> showCategory();

    // 공지사항 상세보기
    public NoticeDTO showDetail(Long noticeId);

    // 공지사항 검색
    public List<NoticeDTO> findByTitleContaining(String keyword);
}
