package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public List<CommentDTO> getCommentList(Long boardId);
    public List<CommentDTO> getMoreComment(Long boardId, int page);
    public void saveComment(CommentDTO commentDTO);
    public void updateComment(CommentDTO commentDTO);
    public void deleteComment(Long commentId);
}
