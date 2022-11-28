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
    private String fileBoardPath;
    @NotNull
    private String fileBoardUuid;

    @Builder
    public FileBoard(String fileBoardName, String fileBoardPath, String fileBoardUuid) {
        this.fileBoardName = fileBoardName;
        this.fileBoardPath = fileBoardPath;
        this.fileBoardUuid = fileBoardUuid;
    }

    public void update(String fileBoardName, String fileBoardPath, String fileBoardUuid) {
        this.fileBoardName = fileBoardName;
        this.fileBoardPath = fileBoardPath;
        this.fileBoardUuid = fileBoardUuid;
    }
}
