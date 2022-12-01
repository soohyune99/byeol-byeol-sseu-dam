package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.FileBoardDTO;
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
    private String fileBoardPath;
    @NotNull
    private String fileBoardUuid;
    @NotNull @ManyToOne
    private Board board;

    public void changeBoard(Board board){
        this.board = board;
    }

    @Builder
    public FileBoard(String fileBoardName, String fileBoardPath, String fileBoardUuid) {
        this.fileBoardName = fileBoardName;
        this.fileBoardPath = fileBoardPath;
        this.fileBoardUuid = fileBoardUuid;
    }

    public void update(FileBoardDTO fileBoardDTO) {
        this.fileBoardName = fileBoardDTO.getFileBoardName();
        this.fileBoardPath = fileBoardDTO.getFileBoardPath();
        this.fileBoardUuid = fileBoardDTO.getFileBoardUuid();
    }
}
