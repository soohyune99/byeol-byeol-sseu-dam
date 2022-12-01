package com.app.byeolbyeolsseudam.domain.fileBoard;

import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileBoardDTO {
    private Long fileBoardId;
    private String fileBoardName;
    private String fileBoardPath;
    private String fileBoardUuid;
    private Long boardId;

    @QueryProjection
    public FileBoardDTO(Long fileBoardId, String fileBoardName, String fileBoardPath, String fileBoardUuid, Long boardId) {
        this.fileBoardId = fileBoardId;
        this.fileBoardName = fileBoardName;
        this.fileBoardPath = fileBoardPath;
        this.fileBoardUuid = fileBoardUuid;
        this.boardId = boardId;
    }

    public FileBoard toEntity(){
        return FileBoard.builder()
                .fileBoardName(fileBoardName)
                .fileBoardPath(fileBoardPath)
                .fileBoardUuid(fileBoardUuid)
                .build();
    }
}
