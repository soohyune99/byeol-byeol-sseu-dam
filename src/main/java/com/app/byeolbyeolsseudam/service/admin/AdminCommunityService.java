package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.repository.admin.community.AdminBoardRepository;
import com.app.byeolbyeolsseudam.repository.admin.community.AdminCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminCommunityService {

    private final AdminBoardRepository adminBoardRepository;
    private final AdminCommentRepository adminCommentRepository;

    public List<BoardDTO> showBoardList(){
        return adminBoardRepository.showBoardList();
    }

    public void removeBoard(List<String> boardIdstr){
        List<Long> boardId = new ArrayList<>();
        boardIdstr.stream().map(Long::parseLong).forEach(boardId::add);
        boardId.forEach(adminBoardRepository::deleteById);
    }

    public List<CommentDTO> showCommentList(){
        return adminCommentRepository.showCommentList();
    }

    public void removeComment(List<String> commentIdstr){
        List<Long> commentId = new ArrayList<>();
        commentIdstr.stream().map(Long::parseLong).forEach(commentId::add);
        commentId.forEach(adminCommentRepository::deleteById);
    }

    public Page<BoardDTO> searchBoard(Pageable pageable){
        List<BoardDTO> boards = adminBoardRepository.searchBoardPaging("", pageable);

        final Page<BoardDTO> boardList = new PageImpl<>(boards, pageable, adminBoardRepository.findAll().size());
        return boardList;

    }

    public Page<BoardDTO> searchBoardPaging(String keyword, Pageable pageable){
        List<BoardDTO> search = adminBoardRepository.searchBoard(keyword);
        List<BoardDTO> boards = adminBoardRepository.searchBoardPaging(keyword, pageable);

        final Page<BoardDTO> boardSearchList = new PageImpl<>(boards,pageable,search.size());
        return boardSearchList;
    }


    public Page<CommentDTO> searchComment(Pageable pageable){
        List<CommentDTO> comments = adminCommentRepository.searchCommentPaging("", pageable);

        final Page<CommentDTO> commentList = new PageImpl<>(comments, pageable, adminCommentRepository.findAll().size());
        return commentList;
    }

    public Page<CommentDTO> searchCommentPaging(String keyword, Pageable pageable){
        List<CommentDTO> search = adminCommentRepository.searchComment(keyword);
        List<CommentDTO> comments = adminCommentRepository.searchCommentPaging(keyword, pageable);

        final Page<CommentDTO> commentSearchList = new PageImpl<>(comments, pageable, search.size());
        return commentSearchList;
    }





}
