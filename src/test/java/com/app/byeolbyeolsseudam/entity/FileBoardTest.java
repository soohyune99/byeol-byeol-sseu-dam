package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.repository.BoardRepository;
import com.app.byeolbyeolsseudam.repository.FileBoardRepository;
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
public class FileBoardTest {
    @Autowired
    private FileBoardRepository fileBoardRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void saveTest(){

    }
}
