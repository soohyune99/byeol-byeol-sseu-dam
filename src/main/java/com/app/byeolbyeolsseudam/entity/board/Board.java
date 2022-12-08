package com.app.byeolbyeolsseudam.entity.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board")
    private List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    private List<FileBoard> files;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeFiles(List<FileBoard> files){
        this.files = files;
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
