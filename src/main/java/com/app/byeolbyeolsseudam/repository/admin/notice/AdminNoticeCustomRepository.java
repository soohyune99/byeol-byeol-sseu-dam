package com.app.byeolbyeolsseudam.repository.admin.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;

import java.util.List;

public interface AdminNoticeCustomRepository {
    public List<NoticeDTO> showList();

}
