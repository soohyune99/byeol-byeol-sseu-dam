package com.app.byeolbyeolsseudam.repository.admin.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.notice.QNoticeDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class AdminNoticeRepositoryImpl implements AdminNoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeDTO> showList() {
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
    }
}
