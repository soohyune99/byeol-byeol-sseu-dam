package com.app.byeolbyeolsseudam.repository.comment;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;

public interface CommentCustomRepository {
    public void saveComment(CommentDTO commentDTO, Comment comment);
}
