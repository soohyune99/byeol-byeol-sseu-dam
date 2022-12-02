package com.app.byeolbyeolsseudam.service.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public interface NoticeService {

    // 공지사항 중요 조회
    public List<NoticeDTO> showListCategory();


    // 공지사항 상세조회
    public NoticeDTO showListDetail(Long noticeId);

    // 공지사항 검색
    public List<NoticeDTO> search(String keyword);

}
