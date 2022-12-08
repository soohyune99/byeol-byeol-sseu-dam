package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Override
    public List<CommentDTO> getCommentList(Long boardId){
        return commentRepository.getCommentList(boardId);
    }

    @Override
    public void saveComment(CommentDTO commentDTO){
        Comment comment = commentDTO.toEntity();
        commentRepository.saveComment(commentDTO, comment);
        commentRepository.save(comment);
    }

    @Override
    public void updateComment(CommentDTO commentDTO){
        Comment comment = commentRepository.findById(commentDTO.getCommentId()).get();
        comment.update(commentDTO);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long commentId){
        commentRepository.delete(commentRepository.findById(commentId).get());
    }

}
