package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;

import java.util.List;

public interface AdminBoardCustomRepository {
    public List<BoardDTO> showBoardList();
}
