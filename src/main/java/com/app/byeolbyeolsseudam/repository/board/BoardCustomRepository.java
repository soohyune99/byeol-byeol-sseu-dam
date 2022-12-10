package com.app.byeolbyeolsseudam.repository.board;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.type.BoardCategory;

import java.util.List;

public interface BoardCustomRepository {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards();
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory);
    public List<BoardDTO> selectBoardsofKeyword(String keyword);
    public List<BoardDTO> selectScrollBoards(Criteria criteria);
    public BoardDTO readBoard(Long boardId);

    public List<BoardDTO> selectBoardsofMypage(Long memberId, int page);

//    public void saveMemberofBoard(BoardDTO boardDTO, Board board);
//    public List<FileBoard> saveFilesofBoard(BoardDTO boardDTO, Board board);
//    public Board updateBoard(BoardDTO boardDTO);
}
