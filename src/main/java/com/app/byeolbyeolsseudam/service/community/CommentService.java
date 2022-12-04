package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment saveComment(CommentDTO commentDTO);
    public void updateComment(CommentDTO commentDTO, Comment comment);
}
