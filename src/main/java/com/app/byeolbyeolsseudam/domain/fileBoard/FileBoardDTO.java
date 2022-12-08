package com.app.byeolbyeolsseudam.domain.fileBoard;

import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class FileBoardDTO {
    private Long fileBoardId;
    private String fileBoardName;
    private String fileBoardPath;
    private String fileBoardUuid;

    @QueryProjection
    public FileBoardDTO(String fileBoardName) {
        this.fileBoardName = fileBoardName;
    }

    @QueryProjection
    public FileBoardDTO(Long fileBoardId, String fileBoardName, String fileBoardPath, String fileBoardUuid, Long boardId) {
        this.fileBoardId = fileBoardId;
        this.fileBoardName = fileBoardName;
        this.fileBoardPath = fileBoardPath;
        this.fileBoardUuid = fileBoardUuid;
    }

    public FileBoard toEntity(Board board){
        return FileBoard.builder()
                .fileBoardName(fileBoardName)
                .board(board)
                .build();
    }
}
