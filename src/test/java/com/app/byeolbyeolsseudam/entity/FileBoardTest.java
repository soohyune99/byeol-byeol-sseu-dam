//package com.app.byeolbyeolsseudam.entity;
//
//import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
//import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
//import com.app.byeolbyeolsseudam.entity.board.Board;
//import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
//import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
//import com.app.byeolbyeolsseudam.repository.fileBoard.FileBoardRepository;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.transaction.annotation.Transactional;
//
//import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
//import static com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard.fileBoard;
//
//@SpringBootTest
//@Slf4j
//@Transactional
//@Rollback(false)
//public class FileBoardTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//    @Autowired
//    private FileBoardRepository fileBoardRepository;
//    @Autowired
//    private BoardRepository boardRepository;
//
//    @Test
//    public void saveTest(){
//        Board findBoard = boardRepository.findAll().get(0);
//
//        for(int i = 0; i < 4; i++){
//            FileBoardDTO fileBoardDTO = new FileBoardDTO();
//
//            fileBoardDTO.setFileBoardName("포켓몬빵" + i + ".png");
//            FileBoard fileBoard = fileBoardDTO.toEntity();
//            fileBoardRepository.save(fileBoard);
//            fileBoard.changeBoard(findBoard);
//
//        }
//    }
//
//    @Test
//    public void findTest(){
//        jpaQueryFactory.select(board.boardId, board.boardTitle, board.boardContent, fileBoard.fileBoardId, fileBoard.fileBoardName)
//                .from(board).join(fileBoard)
//                .on(board.boardId.eq(fileBoard.board.boardId))
//                .fetchJoin()
//                .stream().map(b -> b.toString()).forEach(log::info);
//    }
//
//    @Test
//    public void updateTest(){
//        FileBoardDTO fileBoardDTO = new FileBoardDTO();
//        fileBoardDTO.setFileBoardName("미에로화이바.png");
//
//        jpaQueryFactory.selectFrom(fileBoard).orderBy(fileBoard.fileBoardId.desc())
//                .limit(1).fetchOne().update(fileBoardDTO);
//    }
//
//    @Test
//    public void deleteTest(){
//        jpaQueryFactory.delete(fileBoard)
//                .where(fileBoard.fileBoardName.eq("미에로화이바"))
//                .execute();
//    }
//
//}
