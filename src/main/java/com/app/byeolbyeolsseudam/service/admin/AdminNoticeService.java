package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.repository.admin.notice.AdminNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminNoticeService {
    private final AdminNoticeRepository adminNoticeRepository;

    public List<NoticeDTO> showList(){
        return adminNoticeRepository.showList();
    };

    public void saveNotice(NoticeDTO noticeDTO){
        adminNoticeRepository.save(noticeDTO.toEntity());
    }

    public NoticeDTO selectById(Long noticeId){
        return adminNoticeRepository.selectById(noticeId);
    }

    public void removeNotice(List<String> noticeIdstr){
        List<Long> noticeId = new ArrayList<>();
        noticeIdstr.stream().map(Long::parseLong).forEach(noticeId::add);
        noticeId.forEach(adminNoticeRepository::deleteById);
    }

    public void updateNotice(NoticeDTO noticeDTO, Long noticeId){
        adminNoticeRepository.update(noticeDTO);
        Notice notice = adminNoticeRepository.findById(noticeId).get();
        adminNoticeRepository.save(notice);
    }


}
