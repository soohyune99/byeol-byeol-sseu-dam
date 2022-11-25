package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COMMENT")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends Period {
    @Id @GeneratedValue
    private Long commentId;
    private String commentContent;
    private String commentFile;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;
}
