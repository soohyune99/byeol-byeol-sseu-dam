package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.service.community.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
public class CommentController {
    private final CommentService commentService;

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> saveComment(@RequestBody CommentDTO commentDTO) throws UnsupportedEncodingException {
        log.info("댓글~!!!!!!!!!!!" + commentDTO.getCommentContent());
        log.info("멤버~!!!!!!!!!!!!!!" + commentDTO.getMemberId());
        log.info("보드~!!!!!!!!!!!!" + commentDTO.getBoardId());
        Comment comment = commentService.saveComment(commentDTO);
        commentService.updateComment(commentDTO, comment);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }
}