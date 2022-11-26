package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMENT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Period {
    @Id @GeneratedValue @NotNull
    private Long commentId;
    @NotNull
    private String commentContent;
    @NotNull
    private String commentFile;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public Comment(String commentContent, String commentFile, Member member, Board board) {
        this.commentContent = commentContent;
        this.commentFile = commentFile;
        this.member = member;
        this.board = board;
    }

    public void update(String commentContent, String commentFile){
        this.commentContent = commentContent;
        this.commentFile = commentFile;
    }
}
