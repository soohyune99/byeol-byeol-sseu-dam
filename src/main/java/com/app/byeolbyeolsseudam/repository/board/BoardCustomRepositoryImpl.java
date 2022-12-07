package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.QFileBoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
        BoardDTO boardDTO = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .where(board.boardId.eq(boardId))
                .fetchOne();

        List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate, board.updatedDate))
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
        log.info("보드!!!!!!!!!!!!!!!!!!!" + boardDTO.getBoardId());
        log.info("보드!!!!!!!!!!!!!!!!!!!" + boardDTO.getBoardCategory());
        log.info(boardDTO.getBoardTitle());
        log.info(boardDTO.getBoardContent());
        log.info("보드!!!!!!!!!!!!!!!!!!!" + boardDTO.getMemberId());

        Board updatedBoard = jpaQueryFactory.selectFrom(board)
                .where(board.boardId.eq(boardDTO.getBoardId()))
                .fetchOne();

        updatedBoard.update(boardDTO);

        return updatedBoard;
    }

    @Override
    public List<BoardDTO> selectScrollBoards(int page){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate))
                .from(board)
                .orderBy(board.createdDate.desc())
                .offset(page * 10)
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

    @Override
    public Board plusView(BoardDTO boardDTO){
        boardDTO.setBoardView(boardDTO.getBoardView() + 1);
        Board plusViewBoard = jpaQueryFactory.selectFrom(board).where(board.boardId.eq(boardDTO.getBoardId())).fetchOne();
        plusViewBoard.update(boardDTO);
        return plusViewBoard;
    }

}
