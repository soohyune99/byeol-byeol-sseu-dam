package com.app.byeolbyeolsseudam.entity.fileBoard;

import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.board.Board;
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
    private String fileBoardPath;
    private String fileBoardUuid;
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Builder
    public FileBoard(String fileBoardName, Board board) {
        this.fileBoardName = fileBoardName;
        this.board = board;
    }

//    public void setBoard(Board board){
//        this.board = board;
//        board.getFiles().add(this);
//    }

    public void update(FileBoardDTO fileBoardDTO) {
        this.fileBoardName = fileBoardDTO.getFileBoardName();
        this.fileBoardPath = fileBoardDTO.getFileBoardPath();
        this.fileBoardUuid = fileBoardDTO.getFileBoardUuid();
    }
}
