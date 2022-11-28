package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Board;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String memberProfile;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private List<FileBoardDTO> files;

    @QueryProjection
    public BoardDTO(Long boardId, BoardCategory boardCategory, String boardTitle, String boardContent, int boardView, Long memberId, String memberName, String memberProfile, LocalDateTime createdDate, LocalDateTime updatedDate, List<FileBoardDTO> files) {
        this.boardId = boardId;
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardView = boardView;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberProfile = memberProfile;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.files = files;
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
