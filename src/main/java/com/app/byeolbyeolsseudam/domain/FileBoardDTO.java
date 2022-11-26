package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Board;
import com.app.byeolbyeolsseudam.entity.FileBoard;
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
    private Board board;

    @QueryProjection
    public FileBoardDTO(Long fileBoardId, String fileBoardName, Board board) {
        this.fileBoardId = fileBoardId;
        this.fileBoardName = fileBoardName;
        this.board = board;
    }

    public FileBoard toEntity(){
        return FileBoard.builder()
                .fileBoardName(fileBoardName)
                .board(board)
                .build();
    }
}
