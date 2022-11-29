package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period{
    @Id @GeneratedValue @NotNull
    private Long boardId;
    @Enumerated(EnumType.STRING) @NotNull
    private BoardCategory boardCategory;
    @NotNull
    private String boardTitle;
    @NotNull
    private String boardContent;
    @NotNull
    private int boardView;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Board(BoardCategory boardCategory, String boardTitle, String boardContent, int boardView) {
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardView = boardView;
    }

    public void update(BoardCategory boardCategory, String boardTitle, String boardContent, int boardView){
        this.boardCategory = boardCategory;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardView = boardView;
    }
}
