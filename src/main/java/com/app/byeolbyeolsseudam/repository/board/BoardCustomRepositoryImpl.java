package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.comment.QCommentDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.QFileBoardDTO;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.comment.QComment;
import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
import com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.comment.QComment.comment;
import static com.app.byeolbyeolsseudam.entity.fileBoard.QFileBoard.fileBoard;

@Repository
@RequiredArgsConstructor
public class BoardCustomRepositoryImpl implements BoardCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardDTO> selectTopView(){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate))
                .from(board)
                .orderBy(board.boardView.desc())
                .limit(3)
                .fetch();

        boards.stream().forEach(board -> {
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(comment.commentId, comment.commentContent,
                            comment.member.memberId, comment.board.boardId))
                            .from(comment)
                            .where(comment.commentId.eq(board.getBoardId()))
                            .fetch();
            board.setComments(comments);
        });

        return boards;
    }

    @Override
    public List<BoardDTO> selectBoards(){
        List<BoardDTO> boards = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate))
                .from(board)
                .orderBy(board.createdDate.desc())
                .fetch();

        for(BoardDTO board : boards){
            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                    comment.commentId, comment.commentContent,
                    comment.member.memberId, comment.board.boardId))
                    .from(comment)
                    .where(comment.commentId.eq(board.getBoardId()))
                    .fetch();
            board.setComments(comments);
        }

//        boards.stream().forEach(board -> {
//            List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(comment.commentId, comment.commentContent,
//                    comment.member.memberId, comment.board.boardId))
//                    .from(comment)
//                    .where(comment.commentId.eq(board.getBoardId()))
//                    .fetch();
//            board.setComments(comments);
//        });
        return boards;
    }

    @Override
    public BoardDTO readBoard(Long boardId){
        BoardDTO boardDTO = jpaQueryFactory.select(new QBoardDTO(board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate))
                .from(board)
                .where(board.boardId.eq(boardId))
                .fetchOne();

        List<CommentDTO> comments = jpaQueryFactory.select(new QCommentDTO(
                comment.commentId, comment.commentContent, comment.commentFileName, comment.commentFilePath,
                comment.commentFileUuid, comment.member.memberId, comment.member.memberName,
                comment.member.memberProfileName, comment.member.memberProfilePath, comment.member.memberProfileUuid,
                comment.board.boardId, comment.createdDate))
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
}
