package com.app.byeolbyeolsseudam.entity.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period {
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

    public void update(BoardDTO boardDTO){
        this.boardCategory = boardDTO.getBoardCategory();
        this.boardTitle = boardDTO.getBoardTitle();
        this.boardContent = boardDTO.getBoardContent();
        this.boardView = boardDTO.getBoardView();
    }
}
