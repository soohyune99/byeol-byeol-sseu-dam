package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.type.BoardCategory;

import java.util.List;

public interface BoardCustomRepository {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards();
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory);
    public List<BoardDTO> selectBoardsofKeyword(String keyword);
    public BoardDTO readBoard(Long boardId);
    public void plusView(BoardDTO boardDTO);
}
