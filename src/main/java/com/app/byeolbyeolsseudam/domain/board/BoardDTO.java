package com.app.byeolbyeolsseudam.domain.board;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class BoardDTO {
    private Long boardId;
    private BoardCategory boardCategory;
    private String boardTitle;
    private String boardContent;
    private int boardView;
    private Long memberId;
    private String memberName;
    private String memberProfileName;
    private String memberProfilePath;
    private String memberProfileUuid;
    private LocalDateTime createdDate;

    private List<FileBoardDTO> files;
    private List<CommentDTO> comments;

    @QueryProjection
    public BoardDTO(Long boardId, BoardCategory boardCategory, String boardTitle, String boardContent, int boardView, Long memberId, String memberName, String memberProfileName, String memberProfilePath, String memberProfileUuid, LocalDateTime createdDate) {
        this.boardId = boardId;
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardView = boardView;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberProfileName = memberProfileName;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUuid = memberProfileUuid;
        this.createdDate = createdDate;
    }

    public Board toEntity(){
        return Board.builder()
                .boardCategory(boardCategory)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardView(boardView)
                .build();
    }
}
