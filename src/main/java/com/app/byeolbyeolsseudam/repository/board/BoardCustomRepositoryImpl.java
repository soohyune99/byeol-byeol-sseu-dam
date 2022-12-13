package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.QFileBoardDTO;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;
import static com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard.fileBoard;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    /* 조회수 TOP 3 조회 */
    @Override
    public List<BoardDTO> selectTopView(){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .orderBy(board.boardView.desc())
                .limit(3)
                .fetch();

        boards.stream().forEach(board -> {
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                    comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                    comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                    comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                    comment.board.boardId, comment.createdDate, comment.updatedDate))
                    .from(comment)
                    .where(comment.board.boardId.eq(board.getBoardId()))
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }

    /* 게시글 목록 조회, 검색 및 카테고리 선택 시, 무한스크롤 */
    @Override
    public List<BoardDTO> selectBoards(Criteria criteria){

        List<BoardDTO> boards =  jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .where(
                        titleLike(criteria.getKeyword()), contentLike(criteria.getKeyword()), categoryEq(criteria.getCategory())
                )
                .orderBy(board.createdDate.desc())
                .offset(criteria.getPage() * 10)
                .limit(10)
                .fetch();

        boards.stream().forEach(board -> {
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                    comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                    comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                    comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                    comment.board.boardId, comment.createdDate, comment.updatedDate))
                    .from(comment)
                    .where(comment.board.boardId.eq(board.getBoardId()))
                    .orderBy(comment.createdDate.desc())
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }

    /* 동적쿼리 조건 */
    private BooleanExpression titleLike(String title){
        return StringUtils.hasText(title)? board.boardTitle.contains(title) : null;
    }

    private BooleanExpression contentLike(String content){
        return StringUtils.hasText(content)? board.boardTitle.contains(content) : null;
    }

    private BooleanExpression categoryEq(String category){
        return StringUtils.hasText(category)? board.boardCategory.eq(BoardCategory.valueOf(category)) : null;
    }

    /* 특정 게시글 보기 */
    @Override
    public BoardDTO readBoard(Long boardId){
        BoardDTO boardDTO = jpaQueryFactory.select(new QBoardDTO(QBoard.board.boardId,
                        QBoard.board.boardCategory, QBoard.board.boardTitle,
                        QBoard.board.boardContent, QBoard.board.boardView, QBoard.board.member.memberId,
                        QBoard.board.member.memberName, QBoard.board.member.memberProfileName,
                        QBoard.board.member.memberProfilePath,
                        QBoard.board.member.memberProfileUuid, QBoard.board.createdDate, QBoard.board.updatedDate)
                ).from(QBoard.board)
                .where(QBoard.board.boardId.eq(boardId))
                .fetchOne();

        List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate, comment.updatedDate))
                .from(comment)
                .where(comment.board.boardId.eq(boardId))
                .fetch();


        List<FileBoardDTO> files = jpaQueryFactory.select(new QFileBoardDTO(
                fileBoard.fileBoardId, fileBoard.fileBoardName, fileBoard.fileBoardPath,
                fileBoard.fileBoardUuid, fileBoard.board.boardId))
                .from(fileBoard)
                .where(fileBoard.board.boardId.eq(boardId))
                .fetch();

        boardDTO.setComments(comments);
        boardDTO.setFiles(files);

        return boardDTO;
    }

    /* 마이페이지 내가 쓴 글 조회 */
    @Override
    public List<BoardDTO> selectBoardsofMypage(Long memberId, int page){
        return jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.member.memberId,
                board.createdDate, board.updatedDate))
                .from(board)
                .where(board.member.memberId.eq(memberId))
                .orderBy(board.createdDate.desc())
                .offset(page * 5)
                .limit(5)
                .fetch();
    }
}

