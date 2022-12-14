package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.service.community.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/comment/*", "/comment"})
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{boardId}")
    public List<CommentDTO> getCommentList(@PathVariable Long boardId){
        return commentService.getCommentList(boardId);
    }

    @PostMapping("/{boardId}/{page}")
    public List<CommentDTO> getMoreComment(@PathVariable Long boardId, @PathVariable int page){
        return commentService.getMoreComment(boardId, page);
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> saveComment(@RequestBody CommentDTO commentDTO) throws UnsupportedEncodingException {
        commentService.saveComment(commentDTO);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public void updateComment(@RequestBody CommentDTO commentDTO){
        commentService.updateComment(commentDTO);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }

    @GetMapping("/count/{boardId}")
    public Long countCommentofBoard(@PathVariable Long boardId){
        return commentService.countCommentofBoard(boardId);
    }
}