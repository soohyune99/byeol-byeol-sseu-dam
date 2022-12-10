package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;
import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@Slf4j
@RequiredArgsConstructor
public class CommentCustomRepositoryImpl implements CommentCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CommentDTO> selectCommentList(Long boardId){
        return jpaQueryFactory.select(new QCommentDTO(comment.commentId, comment.commentContent,
                comment.commentFileName, comment.commentFilePath, comment.commentFileUuid,
                comment.member.memberId, comment.member.memberName, comment.member.memberProfileName,
                comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate, comment.updatedDate))
                .from(comment)
                .where(comment.board.boardId.eq(boardId))
                .orderBy(comment.createdDate.desc())
                .limit(5)
                .fetch();
    }

    @Override
    public List<CommentDTO> selectMoreComment(Long boardId, int page){
        return jpaQueryFactory.select(new QCommentDTO(comment.commentId, comment.commentContent,
                comment.commentFileName, comment.commentFilePath, comment.commentFileUuid,
                comment.member.memberId, comment.member.memberName, comment.member.memberProfileName,
                comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate, comment.updatedDate))
                .from(comment)
                .where(comment.board.boardId.eq(boardId))
                .orderBy(comment.createdDate.desc())
                .offset(page * 5)
                .limit(5)
                .fetch();
    }

    @Override
    public List<CommentDTO> selectMyCommentList(Long memberId, int page){
        return jpaQueryFactory.select(new QCommentDTO(comment.commentId,
                comment.commentContent, comment.member.memberId, comment.board.boardId,
                comment.board.boardTitle, comment.createdDate, comment.updatedDate))
                .from(comment)
                .where(comment.member.memberId.eq(memberId))
                .orderBy(comment.createdDate.desc())
                .offset(page * 5)
                .limit(5)
                .fetch();
    }
}
