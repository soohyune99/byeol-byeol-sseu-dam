package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
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
    public BoardDTO readBoard(Long boardId){
        return boardRepository.readBoard(boardId);
    }
}
