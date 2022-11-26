package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_FILE_BOARD")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileBoard extends Period {
    @Id @GeneratedValue @NotNull
    private Long fileBoardId;
    @NotNull
    private String fileBoardName;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public FileBoard(String fileBoardName, Board board) {
        this.fileBoardName = fileBoardName;
        this.board = board;
    }

    public void update(String fileBoardName){
        this.fileBoardName = fileBoardName;
    }
}
