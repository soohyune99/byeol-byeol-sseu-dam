package com.app.byeolbyeolsseudam.repository.admin.notice;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.notice.QNoticeDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;

@Repository
@RequiredArgsConstructor
public class AdminNoticeRepositoryImpl implements AdminNoticeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<NoticeDTO> showList(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.noticeCategory,
                notice.createdDate
        )).from(notice)
                .where(notice.noticeTitle.contains(keyword).or(notice.noticeContent.contains(keyword)))
                .orderBy(notice.noticeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public NoticeDTO selectById(Long noticeID) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId,
                notice.noticeTitle,
                notice.noticeContent,
                notice.noticeCategory,
                notice.createdDate
        )).from(notice)
                .where(notice.noticeId.eq(noticeID))
                .fetchOne();
    }

    @Override
    public void update(NoticeDTO noticeDTO) {
        jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeId.eq(noticeDTO.getNoticeId()))
                .fetchOne().update(noticeDTO);
    }

    @Override
    public List<NoticeDTO> searchNotice(String keyword) {
        return jpaQueryFactory.select(new QNoticeDTO(
                notice.noticeId, notice.noticeTitle,
                notice.noticeContent, notice.noticeCategory, notice.createdDate
        )).from(notice)
                .where(notice.noticeTitle.contains(keyword).or(notice.noticeContent.contains(keyword)))
                .orderBy(notice.noticeId.desc())
                .fetch();
    }
}
