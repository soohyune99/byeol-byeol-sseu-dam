package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminCommentCustomRepository {
    public List<CommentDTO> showCommentList();
    public List<CommentDTO> searchComment(String keyword);
    public List<CommentDTO> searchCommentPaging(String keyword, Pageable pageable);
}
