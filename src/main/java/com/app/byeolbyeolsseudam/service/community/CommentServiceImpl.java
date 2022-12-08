package com.app.byeolbyeolsseudam.service.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.repository.board.BoardRepository;
import com.app.byeolbyeolsseudam.repository.comment.CommentRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public List<CommentDTO> getCommentList(Long boardId){
        return commentRepository.getCommentList(boardId);
    }

    @Override
    public List<CommentDTO> getMoreComment(Long boardId, int page){
        return commentRepository.getMoreComment(boardId, page);
    }

    @Override
    public void saveComment(CommentDTO commentDTO){
        Comment comment = commentDTO.toEntity();
        comment.changeMember(memberRepository.findById(commentDTO.getMemberId()).get());
        comment.changeBoard(boardRepository.findById(commentDTO.getBoardId()).get());
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
