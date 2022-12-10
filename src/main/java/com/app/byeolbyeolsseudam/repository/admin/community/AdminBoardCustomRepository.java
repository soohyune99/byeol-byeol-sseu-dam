package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminBoardCustomRepository {
    public List<BoardDTO> showBoardList();

    public List<BoardDTO> searchBoard(String keyword);
    public List<BoardDTO> searchBoardPaging(String keyword, Pageable pageable);
}
