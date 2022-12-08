package com.app.byeolbyeolsseudam.service.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface NoticeService {

    // 공지사항 일반 10개 조회
    public Page<NoticeDTO> showListAll(Pageable pageable);

    // 공지사항 중요5개, 일반 5개 조회
    public Page<NoticeDTO> showListCategory(Pageable pageable);

    // 공지사항 상세조회
    public NoticeDTO showListDetail(Long noticeId);

    // 공지사항 검색
    public Page<NoticeDTO> search(String keyword, Pageable pageable);

}
