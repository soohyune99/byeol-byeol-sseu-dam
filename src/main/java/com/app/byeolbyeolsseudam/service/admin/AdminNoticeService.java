package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.repository.admin.notice.AdminNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AdminNoticeService {
    private final AdminNoticeRepository adminNoticeRepository;

    public Page<NoticeDTO> showList(Pageable pageable){

       List<NoticeDTO> notices = adminNoticeRepository.showList("",pageable);
        final Page<NoticeDTO> noticePage = new PageImpl<>(notices, pageable, adminNoticeRepository.findAll().size());

        return noticePage;
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

    public Page<NoticeDTO> searchNotice(String keyword, Pageable pageable){
     List<NoticeDTO> search = adminNoticeRepository.searchNotice(keyword);
     List<NoticeDTO> notices = adminNoticeRepository.showList(keyword, pageable);

     final Page<NoticeDTO> searchList = new PageImpl<>(notices, pageable, search.size());

     return searchList;
    }
}
