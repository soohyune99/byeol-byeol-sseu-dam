package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.NoticeDTO;
import com.app.byeolbyeolsseudam.repository.NoticeRepository;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.QNotice.*;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private NoticeRepository noticeRepository;

    @Test
    public void saveTest(){
        NoticeDTO noticeDTO = new NoticeDTO();

        noticeDTO.setNoticeTitle("개미는");
        noticeDTO.setNoticeContent("뚠뚠");
        noticeDTO.setNoticeCategory(NoticeCategory.중요);

        noticeRepository.save(noticeDTO.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Notice> findNotice = noticeRepository.findById(1L);

        if(findNotice.isPresent()){
            Assertions.assertThat(findNotice.get().getNoticeContent().equals("뚠뚠"));

            log.info("setNoticeTitle : " + findNotice.get().getNoticeTitle());
        }
    }

    @Test
    public void updateTest(){
        Optional<Notice> updateNotice = noticeRepository.findById(1L);

        if(updateNotice.isPresent()){
            updateNotice.get().update("오늘도", "뚠뚠", NoticeCategory.일반);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Notice> deleteNotice = noticeRepository.findById(1L);

        if(deleteNotice.isPresent()){
            noticeRepository.delete(deleteNotice.get());
        }
    }


    @Test
    public void queryDslTest(){
        for (int i=0; i<100; i++){
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setNoticeTitle("할리스 커피");
            noticeDTO.setNoticeContent("아메리카노 좋아요.");
            noticeDTO.setNoticeCategory(NoticeCategory.일반);

            noticeRepository.save(noticeDTO.toEntity());
        }

        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeTitle.eq("할리스 커피"))
                .orderBy(notice.noticeId.desc())
                .offset(10)
                .limit(10)
                .fetch();
        notices.stream().map(Notice::toString).forEach(log::info);
    }

    @Test
    public void queryUpdateTest(){
        Notice notices = jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeTitle.eq("할리스 커피"))
                .orderBy(notice.noticeId.desc())
                .limit(1)
                .fetchOne();

        notices.update("스타벅스", "돌체라떼 좋아요",NoticeCategory.중요);
    }

    @Test
    public void queryFindTest(){
        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeTitle.eq("할리스 커피"))
                .limit(5)
                .fetch();

        notices.stream().map(Notice::toString).forEach(log::info);
    }


}
