package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;

import java.util.List;

public interface CommentCustomRepository {
    public List<CommentDTO> getCommentList(Long boardId);
    public List<CommentDTO> getMoreComment(Long boardId, int page);
}
