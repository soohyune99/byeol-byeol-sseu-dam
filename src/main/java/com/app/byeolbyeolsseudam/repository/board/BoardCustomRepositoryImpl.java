package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.QFileBoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
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
import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    /* 조회수 TOP 3 조회 */
    @Override
    public List<BoardDTO> selectTopView(){
//        List<BoardDTO> boards = new ArrayList<>();

//        List<Board> entityBoards = jpaQueryFactory.selectFrom(board)
//                .orderBy(board.boardView.desc())
//                .limit(3)
//                .fetch();

//        entityBoards.stream().map(entity -> new QBoardDTO(board.boardId, board.boardCategory,
//                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
//                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
//                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
//                .forEach(boardDTO -> boards.add(boardDTO.newInstance()));

        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .orderBy(board.boardView.desc())
                .limit(3)
                .fetch();

//        boards.stream().forEach(commentDTO -> {
//
//        });

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

    /* 게시글 목록 조회 */
    @Override
    public List<BoardDTO> selectBoards(){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .orderBy(board.createdDate.desc())
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
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }

    /* 카테고리별 목록 보기 */
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .where(board.boardCategory.eq(boardCategory))
                .orderBy(board.createdDate.desc())
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
                            .fetch();
                    board.setComments(comments);
        });
        return boards;
    }

    /* 검색어별 목록 보기 */
    public List<BoardDTO> selectBoardsofKeyword(String keyword){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .where(board.boardTitle.contains(keyword)
                        .or(board.boardContent.contains(keyword)))
//                        .or(board.boardCategory.eq(BoardCategory.valueOf(keyword))))
                .orderBy(board.createdDate.desc())
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
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }

    /* 특정 게시글 보기 */
    @Override
    public BoardDTO readBoard(Long boardId){

        Board board = jpaQueryFactory.selectFrom(QBoard.board)
                .where(QBoard.board.boardId.eq(boardId))
                .fetchOne();

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
        plusView(boardDTO);

        return boardDTO;
    }

    /* 글 작성 시 작성자 찾기 */
    @Override
    public void saveMemberofBoard(BoardDTO boardDTO, Board board){
        board.changeMember(
                jpaQueryFactory.selectFrom(member)
                .where(member.memberId.eq(boardDTO.getMemberId()))
                .fetchOne()
        );
    }

    /* 게시글 수정 */
    @Override
    public Board updateBoard(BoardDTO boardDTO){
        Board updatedBoard = jpaQueryFactory.selectFrom(board)
                .where(board.boardId.eq(boardDTO.getBoardId()))
                .fetchOne();

        updatedBoard.update(boardDTO);

        return updatedBoard;
    }

    /* 무한스크롤 */
    @Override
    public List<BoardDTO> selectScrollBoards(Criteria criteria){
        List<BoardDTO> boards =  jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .where(
                        titleLike(criteria.getKeyword()), contentLike(criteria.getKeyword()), categoryEq(criteria.getCategory())
                        )
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
                    .fetch();
            board.setComments(comments);
        });
        return boards;
    }

    /* 무한스크롤 동적쿼리 조건 */
    private BooleanExpression titleLike(String title){
        return StringUtils.hasText(title)? board.boardTitle.contains(title) : null;
    }

    private BooleanExpression contentLike(String content){
        return StringUtils.hasText(content)? board.boardTitle.contains(content) : null;
    }

    private BooleanExpression categoryEq(String category){
        return StringUtils.hasText(category)? board.boardCategory.eq(BoardCategory.valueOf(category)) : null;
    }

    @Override
    public Board plusView(BoardDTO boardDTO){
        boardDTO.setBoardView(boardDTO.getBoardView() + 1);
        Board plusViewBoard = jpaQueryFactory.selectFrom(board).where(board.boardId.eq(boardDTO.getBoardId())).fetchOne();
        plusViewBoard.update(boardDTO);
        return plusViewBoard;
    }

}
