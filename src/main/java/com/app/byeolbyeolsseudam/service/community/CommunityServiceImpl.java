package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService{
    private final BoardRepository boardRepository;

    @Override
    public List<BoardDTO> selectTopView(){
        return boardRepository.selectTopView();
    }

    @Override
    public List<BoardDTO> selectBoards(){
        return boardRepository.selectBoards();
    }

    @Override
    public List<BoardDTO> selectBoardsofCategory(BoardCategory boardCategory){
        return boardRepository.selectBoardsofCategory(boardCategory);
    }

    @Override
    public List<BoardDTO> selectBoardsofKeyword(String keyword){
        return boardRepository.selectBoardsofKeyword(keyword);
    }

    @Override
    public BoardDTO readBoard(Long boardId){
        return boardRepository.readBoard(boardId);
    }

    @Override
    public void saveBoard(BoardDTO boardDTO) {
        Board board = boardDTO.toEntity();
        boardRepository.saveMemberofBoard(boardDTO, board);
        boardRepository.save(board);
    }

    @Override
    public Long updateBoard(BoardDTO boardDTO){
        Board board = boardRepository.updateBoard(boardDTO);
        boardRepository.save(board);
        return boardDTO.getBoardId();
    }

    @Override
    public void deleteBoard(Long boardId){
        boardRepository.delete(boardRepository.findById(boardId).get());
    }

    @Override
    public List<BoardDTO> selectScrollBoards(int page){
        return boardRepository.selectScrollBoards(page);
    }

//    @Override
//    public void plusView(BoardDTO boardDTO){
//        Board board = boardRepository.plusView(boardDTO);
//        boardRepository.save(board);
//    }
}
