package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.MyprogramRepository;
import com.app.byeolbyeolsseudam.repository.ProgramRepository;
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
public class MyprogramTest {
    @Autowired
    private MyprogramRepository myprogramRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){

    }
}
