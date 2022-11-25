package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_BOARD")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends Period{
    @Id @GeneratedValue
    private Long boardId;
    @Enumerated(EnumType.STRING)
    private BoardCategory boardCategory;
    private String boardTitle;
    private String boardContent;
    private int boardView;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
