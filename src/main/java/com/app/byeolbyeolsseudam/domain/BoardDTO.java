package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Board;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BoardDTO {
    private Long boardId;
    private BoardCategory boardCategory;
    private String boardTitle;
    private String boardContent;
    private int boardView;
    private Member member;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public BoardDTO(Long boardId, BoardCategory boardCategory, String boardTitle, String boardContent, int boardView, Member member, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.boardId = boardId;
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardView = boardView;
        this.member = member;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Board toEntity(){
        return Board.builder()
                .boardCategory(boardCategory)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardView(boardView)
                .member(member)
                .build();
    }
}
