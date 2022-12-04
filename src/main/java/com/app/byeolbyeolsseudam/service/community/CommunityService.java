package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards();
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory);
    public List<BoardDTO> selectBoardsofKeyword(String keyword);
    public BoardDTO readBoard(Long boardId);
    //    public void plusView(BoardDTO boardDTO);
    public void saveBoard(BoardDTO boardDTO);
}
