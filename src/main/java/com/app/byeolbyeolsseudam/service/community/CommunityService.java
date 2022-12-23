package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommunityService {
    public List<BoardDTO> selectTopView();
    public List<BoardDTO> selectBoards(Criteria criteria);
    public BoardDTO readBoard(Long boardId);
    public void updateBoard(BoardDTO boardDTO);
    public void deleteBoard(Long boardId);
    public void saveBoard(BoardDTO boardDTO);
    public Long plusView(Long boardId);
}
