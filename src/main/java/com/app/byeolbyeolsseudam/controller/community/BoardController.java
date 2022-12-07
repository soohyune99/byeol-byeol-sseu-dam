package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.fileBoard.FileBoardDTO;
import com.app.byeolbyeolsseudam.entity.fileBoard.FileBoard;
import com.app.byeolbyeolsseudam.service.community.CommunityService;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
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

    @GetMapping(value = {"/read/{boardId}", "update/{boardId}"})
    public BoardDTO getBoardDetail(@PathVariable Long boardId){
        return communityService.readBoard(boardId);
    }

    @PostMapping("/write")
    public void saveBoard(@RequestBody BoardDTO boardDTO){
        communityService.saveBoard(boardDTO);
    }

    @PatchMapping("/update/{boardId}")
    public Long updateBoard(@RequestBody BoardDTO boardDTO, @PathVariable Long boardId){
        communityService.updateBoard(boardDTO);
        return boardId;
    }

//    @PatchMapping("/view/{boardId}")
//    public void plusBoardView(@PathVariable Long boardId, @RequestBody BoardDTO boardDTO){
//        communityService.plusView(boardDTO);
//    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId){
        communityService.deleteBoard(boardId);
    }

    @GetMapping("/scroll/{page}")
    public List<BoardDTO> infiniteScroll(@PathVariable int page){
        return communityService.selectScrollBoards(page);
    }

    @ResponseBody
    @PostMapping("/upload")
    public void uploadBoardFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=utf-8");
        String uploadPath = "C:/upload/";

        PrintWriter out = response.getWriter();
        String originalFileExtension = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "");// + originalFileExtension
        log.info("storedFileName : " + storedFileName);
        log.info("originalFileExtension : " + originalFileExtension);
        log.info(file.toString());
        log.info(uploadPath+storedFileName);
        file.transferTo(new File(uploadPath + storedFileName + originalFileExtension));
        out.println("/upload/" + storedFileName + originalFileExtension);
        out.close();
    }
}
