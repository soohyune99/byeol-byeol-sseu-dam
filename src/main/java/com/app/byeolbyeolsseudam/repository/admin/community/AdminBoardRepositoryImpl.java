package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.board.QBoardDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;

@Repository
@RequiredArgsConstructor
public class AdminBoardRepositoryImpl implements AdminBoardCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BoardDTO> showBoardList() {
        return jpaQueryFactory.select(new QBoardDTO(
                board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate
        )).from(board).orderBy(board.boardId.desc()).limit(10).fetch();
    }

    @Override
    public List<BoardDTO> searchBoard(String keyword) {
        return jpaQueryFactory.select(new QBoardDTO(
                board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate
        )).from(board)
                .where(board.boardTitle.contains(keyword).or(board.boardContent.contains(keyword)))
                .orderBy(board.boardId.desc())
                .fetch();
    }

    @Override
    public List<BoardDTO> searchBoardPaging(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QBoardDTO(
                board.boardId, board.boardCategory,
                board.boardTitle, board.boardContent, board.boardView, board.member.memberId,
                board.member.memberName, board.member.memberProfileName, board.member.memberProfilePath,
                board.member.memberProfileUuid, board.createdDate, board.updatedDate
        )).from(board)
                .where(board.boardTitle.contains(keyword).or(board.boardContent.contains(keyword)))
                .orderBy(board.boardId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
