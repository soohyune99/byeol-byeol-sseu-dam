package com.app.byeolbyeolsseudam.repository.admin.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;

import java.util.List;

public interface AdminCommentCustomRepository {
    public List<CommentDTO> showCommentList();
}
