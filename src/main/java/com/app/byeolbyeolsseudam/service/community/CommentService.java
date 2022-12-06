package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public void saveComment(CommentDTO commentDTO);
}
