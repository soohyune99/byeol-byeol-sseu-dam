package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.entity.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBoardRepository extends JpaRepository<Board, Long>, AdminBoardCustomRepository {

}
