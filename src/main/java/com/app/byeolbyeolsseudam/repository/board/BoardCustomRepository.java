package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;

import java.util.List;

public interface BoardCustomRepository {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards();
    public BoardDTO readBoard(Long boardId);
}
