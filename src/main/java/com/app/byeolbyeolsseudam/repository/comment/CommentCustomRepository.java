package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;

import java.util.List;

public interface CommentCustomRepository {
    public List<CommentDTO> selectCommentList(Long boardId);
    public List<CommentDTO> selectMoreComment(Long boardId, int page);
    public List<CommentDTO> selectMyCommentList(Long memberId, int page);
}
