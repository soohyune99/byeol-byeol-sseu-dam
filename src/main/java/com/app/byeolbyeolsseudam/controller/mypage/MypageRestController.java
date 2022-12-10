package com.app.byeolbyeolsseudam.controller.mypage;

import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.service.mypage.MypageService;
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
@RequestMapping("/mypage/*")
public class MypageRestController {
    private final MypageService mypageService;

    @GetMapping("/program/{memberId}/{page}")
    public List<MyprogramDTO> getMyprogramList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyprogramList(memberId, page);
    }

    @GetMapping("/point/{memberId}/{page}")
    public List<MypointDTO> getMypointList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMypointList(memberId, page);
    }

    @GetMapping("/community/{memberId}/{page}")
    public List<BoardDTO> getMyCommunityList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyCommunityList(memberId, page);
    }

    @GetMapping("/comment/{memberId}/{page}")
    public List<CommentDTO> getMyCommentList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyCommentList(memberId, page);
    }

    @GetMapping("/{memberId}")
    public MemberDTO getMyInfo(@PathVariable Long memberId){
        return mypageService.getMyInfo(memberId);
    }

    @GetMapping("/order/{memberId}/{page}")
    public List<OrderDTO> getMyOrderList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyOrderList(memberId, page);
    }

    @GetMapping("/cancel/{memberId}/{page}")
    public List<OrderDTO> getMyCancelList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyCancelList(memberId, page);
    }

    @GetMapping("/cancel/{orderId}")
    public OrderDTO getMyCancel(@PathVariable Long orderId){
        return mypageService.getMyOrder(orderId);
    }

    @ResponseBody
    @PostMapping("/upload")
    public void uploadMemberProfileFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=utf-8");
        String uploadPath = "C:/upload/member/";

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
        out.print("/upload/member/" + createDirectoryByNow() + "/" + storedFileName + originalFileExtension);
        out.close();
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }

}
