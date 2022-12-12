package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.entity.badge.Badge;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
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

//        for(int i = 0; i < 6; i++){
            BadgeDTO badgeDTO = new BadgeDTO();

            badgeDTO.setBadgeName("줍깅 배지6");
            badgeDTO.setBadgeInfo("줍깅 챌린지 30회 완주");
            badgeDTO.setBadgeFileName("badge.png");

            badgeRepository.save(badgeDTO.toEntity());
//        }
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
        BadgeDTO badgeDTO = new BadgeDTO();
        badgeDTO.setBadgeName("수정된 배지");

        if(updateBadge.isPresent()){
            updateBadge.get().update(badgeDTO);
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
