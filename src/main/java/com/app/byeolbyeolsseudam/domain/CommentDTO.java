package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Board;
import com.app.byeolbyeolsseudam.entity.Comment;
import com.app.byeolbyeolsseudam.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CommentDTO {
    private Long commentId;
    private String commentContent;
    private String commentFile;
    private Member member;
    private Board board;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public CommentDTO(Long commentId, String commentContent, String commentFile, Member member, Board board, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.commentId = commentId;
        this.commentContent = commentContent;
        this.commentFile = commentFile;
        this.member = member;
        this.board = board;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Comment toEntity(){
        return Comment.builder()
                .commentContent(commentContent)
                .commentFile(commentFile)
                .member(member)
                .board(board)
                .build();
    }
}
