package com.app.byeolbyeolsseudam.repository.admin.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminNoticeCustomRepository {
    public List<NoticeDTO> showList(String keyword,Pageable pageable);
    public void update(NoticeDTO noticeDTO);
    public NoticeDTO selectById(Long noticeID);
    public List<NoticeDTO> searchNotice(String keyword);
}
