package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BoardDTO;
import com.app.byeolbyeolsseudam.domain.MemberDTO;
import com.app.byeolbyeolsseudam.repository.BoardRepository;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
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
        BoardDTO boardDTO = new BoardDTO();
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberLoginType(MemberLoginType.네이버);
        memberDTO.setMemberCategory(MemberCategory.일반회원);
        memberDTO.setMemberName("은지");
        memberDTO.setMemberEmail("sej07@naver.com");
        memberDTO.setMemberPassword("password486!");
        memberDTO.setMemberAddress("역삼역 3번 출구");
        memberDTO.setMemberPhone("01012345678");
        memberDTO.setMemberPoint(3000);
        memberDTO.setMemberProfileFile("noimage.png");

        memberRepository.save(memberDTO.toEntity());

        boardDTO.setBoardCategory(BoardCategory.환경활동);
        boardDTO.setBoardTitle("안녕하세요");
        boardDTO.setBoardContent("집 인터넷이 안돼요.. 왜 이럴까요");
        boardDTO.setBoardView(1);
        boardDTO.setMember(memberRepository.findAll().get(0));

        boardRepository.save(boardDTO.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Board> findBoard = boardRepository.findById(5L);

        if(findBoard.isPresent()){
            Assertions.assertThat(findBoard.get().getMember().getMemberName().equals("은지"));
        }

        log.info("BoardTitle : " + findBoard.get().getBoardTitle());
        log.info("BoardContent : " + findBoard.get().getBoardContent());
    }

    @Test
    public void updateTest(){
        Optional<Board> updateBoard = boardRepository.findById(5L);

        if(updateBoard.isPresent()){
            updateBoard.get().update(BoardCategory.추천가게, "하하하", "야식먹구싶다", 12);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Board> deleteBoard = boardRepository.findById(5L);

        if(deleteBoard.isPresent()){
            boardRepository.delete(deleteBoard.get());
        }
    }

}
