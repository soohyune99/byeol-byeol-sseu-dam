package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.notice.QNoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.entity.notice.QNotice;
import com.app.byeolbyeolsseudam.repository.notice.NoticeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;

@Service
@RequiredArgsConstructor
public class AdminNoticeService {
    private final JPAQueryFactory jpaQueryFactory;
    private final NoticeRepository noticeRepository;

    public List<NoticeDTO> showList(){
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.noticeCategory,
                notice.createdDate
        )).from(notice)
                .orderBy(notice.noticeId.desc())
                .limit(10)
                .fetch();
    };

    public void saveNotice(NoticeDTO noticeDTO){
        noticeRepository.save(noticeDTO.toEntity());
    }

}
