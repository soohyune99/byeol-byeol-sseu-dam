package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.FileBoardDTO;
import com.app.byeolbyeolsseudam.repository.BoardRepository;
import com.app.byeolbyeolsseudam.repository.FileBoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.QFileBoard.fileBoard;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FileBoardTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private FileBoardRepository fileBoardRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void saveTest(){
        Optional<Board> findBoard = boardRepository.findById(2L);

        FileBoardDTO fileBoardDTO = new FileBoardDTO();

        fileBoardDTO.setFileBoardName("친환경에코상점.png");
        fileBoardDTO.setBoard(findBoard.get());

        fileBoardRepository.save(fileBoardDTO.toEntity());
    }

    @Test
    public void findTest(){
        List<FileBoard> files = jpaQueryFactory.selectFrom(fileBoard)
                .orderBy(fileBoard.fileBoardId.desc())
                .limit(2)
                .fetch();

        files.stream().map(FileBoard::getFileBoardName).forEach(log::info);
    }

    @Test
    public void updateTest(){
        jpaQueryFactory.selectFrom(fileBoard).orderBy(fileBoard.fileBoardId.desc())
                .limit(1).fetchOne().update("미에로화이바");
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(fileBoard)
                .where(fileBoard.fileBoardName.eq("미에로화이바"))
                .execute();
    }

}
