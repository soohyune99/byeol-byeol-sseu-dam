package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
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
public class CommentTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){

        for(int i = 0; i < 5; i++){
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setCommentContent("새로운 댓글!" + i);
            commentDTO.setCommentFileName("hi.png");
            commentDTO.setCommentFilePath("/upload");
            commentDTO.setCommentFileUuid("abcedfg");

            Comment comment = commentDTO.toEntity();

            commentRepository.save(comment);

            comment.changeMember(memberRepository.findAll().get(0));
            comment.changeBoard(boardRepository.findById(116L).get());
        }
    }

    @Test
    public void findTest(){
        Optional<Comment> findComment = commentRepository.findById(8L);

        if(findComment.isPresent()){
            Assertions.assertThat(findComment.get().getMember().getMemberName().equals("은지"));

            log.info("CommentContent : " + findComment.get().getCommentContent());
            log.info("BoardTitle : " + findComment.get().getBoard().getBoardTitle());
        }
    }

    @Test
    public void updateTest(){
        Optional<Comment> updateComment = commentRepository.findById(8L);
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setCommentContent("수정댓글");

        if(updateComment.isPresent()){
            updateComment.get().update(commentDTO);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Comment> deleteComment = commentRepository.findById(8L);

        if(deleteComment.isPresent()){
            Assertions.assertThat(deleteComment.get().getMember().getMemberName().equals("은지"));

            log.info("CommentContent : " + deleteComment.get().getCommentContent());
            log.info("BoardTitle : " + deleteComment.get().getBoard().getBoardTitle());
        }
    }
}
