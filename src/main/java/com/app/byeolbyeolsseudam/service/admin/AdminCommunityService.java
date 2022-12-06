package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.repository.admin.community.AdminBoardRepository;
import com.app.byeolbyeolsseudam.repository.admin.community.AdminCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommunityService {

    private final AdminBoardRepository adminBoardRepository;
    private final AdminCommentRepository adminCommentRepository;

    public List<BoardDTO> showBoardList(){
        return adminBoardRepository.showBoardList();
    }

    public List<CommentDTO> showCommentList(){
        return adminCommentRepository.showCommentList();
    }
}
