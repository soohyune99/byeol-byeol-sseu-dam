package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CommentDTO;
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
    private String commentFileName;
    private String commentFilePath;
    private String commentFileUuid;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeBoard(Board board){
        this.board = board;
    }

    @Builder
    public Comment(String commentContent, String commentFileName, String commentFilePath, String commentFileUuid) {
        this.commentContent = commentContent;
        this.commentFileName = commentFileName;
        this.commentFilePath = commentFilePath;
        this.commentFileUuid = commentFileUuid;
    }

    public void update(String commentContent, String commentFileName, String commentFilePath, String commentFileUuid) {
        this.commentContent = commentContent;
        this.commentFileName = commentFileName;
        this.commentFilePath = commentFilePath;
        this.commentFileUuid = commentFileUuid;
    }
}
