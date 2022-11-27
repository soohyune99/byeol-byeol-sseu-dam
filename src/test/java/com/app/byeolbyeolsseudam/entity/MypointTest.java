package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MypointDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MypointRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MypointTest {
    @Autowired
    private MypointRepository mypointRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MypointDTO mypointDTO = new MypointDTO();

        mypointDTO.setMypointContent("신규회원가입");
        mypointDTO.setMypointInout(3000);
        mypointDTO.setMember(memberRepository.findAll().get(0));

        mypointRepository.save(mypointDTO.toEntity());
    }

}
