package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BadgeDTO;
import com.app.byeolbyeolsseudam.repository.BadgeRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BadgeTest {
    @Autowired
    private BadgeRepository badgeRepository;

    @Test
    public void saveTest(){

        for(int i = 0; i < 50; i++){
            BadgeDTO badgeDTO = new BadgeDTO();

            badgeDTO.setBadgeName("배지" + i);
            badgeDTO.setBadgeFileName("test.png");
            badgeDTO.setBadgeFilePath("/upload");
            badgeDTO.setBadgeFileUuid("aabbcc" + i);

            badgeRepository.save(badgeDTO.toEntity());
        }
    }

    @Test
    public void findTest(){
        Optional<Badge> findBadge = badgeRepository.findById(1L);

        if(findBadge.isPresent()){
            Assertions.assertThat(findBadge.get().getBadgeName().equals("배지1"));

            log.info("badgeName : " + findBadge.get().getBadgeName());
            log.info("badgeFile : " + findBadge.get().getBadgeFileName());
        }
    }

    @Test
    public void updateTest(){
        Optional<Badge> updateBadge = badgeRepository.findById(1L);

        if(updateBadge.isPresent()){
            updateBadge.get().update("첫번째 배지", "first.png",
                    "/upload", "firstBadge");
        }
    }

    @Test
    public void deleteTest(){
        Optional<Badge> deleteBadge = badgeRepository.findById(50L);

        if(deleteBadge.isPresent()){
            badgeRepository.delete(deleteBadge.get());
        }
    }
}
