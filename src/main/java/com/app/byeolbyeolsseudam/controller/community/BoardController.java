package com.app.byeolbyeolsseudam.controller.community;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.service.community.CommunityService;
import com.app.byeolbyeolsseudam.type.BoardCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/board/*", "/board"})
public class BoardController {
    private final CommunityService communityService;

    @PostMapping("")
    public List<BoardDTO> getBoardList(Criteria criteria) {
        return communityService.selectBoards(criteria);
    }

    @GetMapping("/topview")
    public List<BoardDTO> getTopViewList() {
       return communityService.selectTopView();
    }

    @GetMapping(value = {"/read/{boardId}", "update/{boardId}"})
    public BoardDTO getBoardDetail(@PathVariable Long boardId){
        return communityService.readBoard(boardId);
    }

    @PostMapping("/write")
    public void saveBoard(BoardDTO boardDTO){
        communityService.saveBoard(boardDTO);
    }

    @PostMapping("/update/{boardId}")
    public Long updateBoard(BoardDTO boardDTO, @PathVariable Long boardId){
        communityService.updateBoard(boardDTO);
        return boardId;
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoard(@PathVariable Long boardId){
        communityService.deleteBoard(boardId);
    }

    @PatchMapping("/view/{boardId}")
    public Long plusBoardView(@PathVariable Long boardId){
        return communityService.plusView(boardId);
    }

    @ResponseBody
    @PostMapping("/upload")
    public void uploadBoardFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=utf-8");
        String uploadPath = "C:/upload/community/";

        File uploadFolder = new File(uploadPath, createDirectoryByNow());
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }

        PrintWriter out = response.getWriter();
        String originalFileExtension = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "");// + originalFileExtension
        log.info("storedFileName : " + storedFileName);
        log.info("originalFileExtension : " + originalFileExtension);
        log.info(file.toString());
        log.info(uploadPath + storedFileName);
        file.transferTo(new File(uploadFolder + "/" + storedFileName + originalFileExtension));
        out.print("/upload/community/" + createDirectoryByNow() + "/" + storedFileName + originalFileExtension);
        out.close();
    }

    @PostMapping("/delete")
    public void deleteBoardFile(String fileName){
        File file = new File("C:", fileName);

        if(file.exists()){
            file.delete();
        }
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }
}
