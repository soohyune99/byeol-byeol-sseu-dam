package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.entity.notice.QNotice;
import com.app.byeolbyeolsseudam.repository.notice.NoticeRepository;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.notice.QNotice.notice;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeTest {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private NoticeRepository noticeRepository;

    // 공지사항 save
    @Test
    public void queryDslTest(){

        for (int i=0; i<10; i++){
            NoticeDTO notice1 = new NoticeDTO();
            notice1.setNoticeTitle("중요 공지사항" + (i+1));
            notice1.setNoticeContent("이 공지사항은 중요합니다.\n 꼼꼼히 읽어주세요. 감사합니다." + (i+1));
            notice1.setNoticeCategory(NoticeCategory.중요);

            noticeRepository.save(notice1.toEntity());
        }
        for (int i=11; i<20; i++){
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setNoticeTitle("일반 공지사항" + (i+1));
            noticeDTO.setNoticeContent("이 공지사항은 보통입니다.\n 그래도 꼼꼼히 읽어주세요. 감사합니다." + (i+1));
            noticeDTO.setNoticeCategory(NoticeCategory.일반);

            noticeRepository.save(noticeDTO.toEntity());
        }
        for (int i=21; i<30; i++){
            NoticeDTO notice2 = new NoticeDTO();
            notice2.setNoticeTitle("일반 공지사항" + (i+1));
            notice2.setNoticeContent("이 공지사항은 보통입니다.\n 그래도 꼼꼼히 읽어주세요. 감사합니다." + (i+1));
            notice2.setNoticeCategory(NoticeCategory.일반);

            noticeRepository.save(notice2.toEntity());
        }

//
//        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
//                .where(notice.noticeTitle.eq("공지사항"))
//                .orderBy(notice.noticeId.desc())
//                .offset(10)
//                .limit(5)
//                .fetch();
//        notices.stream().map(Notice::toString).forEach(log::info);
    }

    // 카테고리가 중요인 공지사항 5개 조회
    @Test
    public void findCategoryTest(){
        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeCategory.eq(NoticeCategory.중요))
                .orderBy(notice.createdDate.desc())
                .limit(5)
                .fetch();

        for(int i = 0; i < 5; i++){
            log.info("noticeTitle : " + notices.get(i).getNoticeTitle());
            log.info("NoticeContent : " + notices.get(i).getNoticeContent());
        }
    }

    // 카테고리가 일반인 공지사항 5개 조회
    @Test
    public void findCategoryNomalTest(){
        List<Notice> notices = jpaQueryFactory.selectFrom(notice)
                .where(notice.noticeCategory.eq(NoticeCategory.일반))
                .orderBy(notice.createdDate.desc())
                .limit(5)
                .fetch();

        for(int i = 0; i < 5; i++){
            log.info("noticeTitle : " + notices.get(i).getNoticeTitle());
            log.info("NoticeContent : " + notices.get(i).getNoticeContent());
        }
    }

    // 공지사항 클릭 시 상세보기
    @Test
    public void findById(){
        Optional<Notice> findByIdNotice = noticeRepository.findById(7L);

        log.info("noticeTitle : " + findByIdNotice.get().getNoticeTitle());
        log.info("NoticeContent : " + findByIdNotice.get().getNoticeContent());
    }

    // 검색
    @Test
    public void searchTest(){
        jpaQueryFactory.select(notice.noticeTitle, notice.noticeContent)
                .from(notice)
                .where(notice.noticeTitle.contains("스").or(notice.noticeContent.contains("스")))
                .orderBy(notice.createdDate.desc())
                .fetch()
                .stream()
                .forEach(n -> log.info("notice : " + n));

//        for (int i = 0; i < notices.size(); i++){
//            log.info("noticeTitle : " + notices.get(i).getNoticeTitle());
//            log.info("NoticeContent : " + notices.get(i).getNoticeContent());
//        }


    }

    @Test
    public void deleteTest(){
        Optional<Notice> deleteNotice = noticeRepository.findById(1L);

        if(deleteNotice.isPresent()){
            noticeRepository.delete(deleteNotice.get());
        }
    }
}
