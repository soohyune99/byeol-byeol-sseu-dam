package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.NoticeDTO;
import com.app.byeolbyeolsseudam.repository.NoticeRepository;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class NoticeTest {

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



}
