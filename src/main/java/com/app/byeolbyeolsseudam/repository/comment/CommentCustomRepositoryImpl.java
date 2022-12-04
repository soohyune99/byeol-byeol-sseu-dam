package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public void saveComment(CommentDTO commentDTO, Comment comment){
        log.info("boardId" + commentDTO.getBoardId());
        log.info("memberId" + commentDTO.getMemberId());

        Board board = jpaQueryFactory.selectFrom(QBoard.board)
                .where(QBoard.board.boardId.eq(commentDTO.getBoardId()))
                .fetchOne();

        comment.changeBoard(board);

//        comment.changeBoard(
//                jpaQueryFactory.selectFrom(board)
//                        .where(board.boardId.eq(commentDTO.getBoardId()))
//                        .fetchOne()
//        );
//        comment.changeMember(
//                jpaQueryFactory.selectFrom(member)
//                        .where(member.memberId.eq(commentDTO.getMemberId()))
//                        .fetchOne()
//        );
    }
}
