package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public Comment saveComment(CommentDTO commentDTO){
        Comment comment = commentDTO.toEntity();
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void updateComment(CommentDTO commentDTO, Comment comment){
        commentRepository.saveComment(commentDTO, comment);
    }

}
