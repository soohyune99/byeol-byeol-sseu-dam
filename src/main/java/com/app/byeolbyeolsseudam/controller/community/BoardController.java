package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.service.community.CommunityService;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {"/board/*", "/board"})
public class BoardController {
    private final CommunityService communityService;

    @GetMapping("")
    public List<BoardDTO> getBoardList() {
        return communityService.selectBoards();
    }

    @GetMapping("/topview")
    public List<BoardDTO> getTopViewList() {
       return communityService.selectTopView();
    }

    @GetMapping("/{boardCategory}")
    public List<BoardDTO> getCategoryBoards(@PathVariable BoardCategory boardCategory) {
        return communityService.selectBoardsofCategory(boardCategory);
    }

    @PostMapping("/{keyword}")
    public List<BoardDTO> getSearchBorads(@PathVariable String keyword){
        return communityService.selectBoardsofKeyword(keyword);
    }

    @GetMapping("/read/{boardId}")
    public BoardDTO getBoardDetail(@PathVariable Long boardId){
        return communityService.readBoard(boardId);
    }
}
