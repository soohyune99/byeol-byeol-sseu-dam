package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
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
public class BoardTest {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberLoginType(MemberLoginType.네이버);
        memberDTO.setMemberCategory(MemberCategory.일반회원);
        memberDTO.setMemberName("성은지");
        memberDTO.setMemberEmail("sej07@naver.com");
        memberDTO.setMemberPassword("password486!");
        memberDTO.setMemberAddress("역삼역 3번 출구");
        memberDTO.setMemberPhone("01012345678");
        memberDTO.setMemberPoint(3000);

        Member member = memberDTO.toEntity();
        memberRepository.save(member);

        for(int i = 0; i < 10; i++){
            BoardDTO boardDTO = new BoardDTO();

            boardDTO.setBoardCategory(BoardCategory.환경활동);
            boardDTO.setBoardTitle("안녕하세요" + i);
            boardDTO.setBoardContent("날씨가 추워요" + i);
            boardDTO.setBoardView(i * 10);

            Board board = boardDTO.toEntity();
            boardRepository.save(board);

            board.changeMember(member);
        }

    }

    @Test
    public void findTest(){
        Optional<Board> findBoard = boardRepository.findById(52L);

        if(findBoard.isPresent()){
            Assertions.assertThat(findBoard.get().getMember().getMemberName().equals("은지"));
        }

        log.info("BoardTitle : " + findBoard.get().getBoardTitle());
        log.info("BoardContent : " + findBoard.get().getBoardContent());
    }

    @Test
    public void updateTest(){
        Optional<Board> updateBoard = boardRepository.findById(52L);
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setBoardTitle("야식먹구싶다");

        if(updateBoard.isPresent()){
            updateBoard.get().update(boardDTO);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Board> deleteBoard = boardRepository.findById(54L);

        if(deleteBoard.isPresent()){
            boardRepository.delete(deleteBoard.get());
        }
    }

}
