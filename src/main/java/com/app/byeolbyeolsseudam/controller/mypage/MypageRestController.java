package com.app.byeolbyeolsseudam.controller.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.board.BoardDTO;
import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import com.app.byeolbyeolsseudam.service.mypage.MypageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

    @PostMapping("/check/{memberId}")
    public Boolean checkPassword(@PathVariable Long memberId, @RequestBody String password){
        return mypageService.checkPassword(memberId, password);
    }

    @PostMapping("/update")
    public MemberDTO updateMyinfo(MemberDTO memberDTO){
        return mypageService.updateMyInfo(memberDTO);
    }

    @PostMapping("/send/{phoneNumber}")
    public String sendVerification(@PathVariable String phoneNumber){
        return mypageService.sendVerificationNumber(phoneNumber);
    }

    @GetMapping("/dropout/{memberId}")
    public void dropOutMember(@PathVariable Long memberId){
        mypageService.dropOutMember(memberId);
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
    public OrderDTO getMyOrder(@PathVariable Long orderId){
        return mypageService.getMyOrder(orderId);
    }

    @GetMapping("/cancelOrder/{orderId}")
    public Long cancelMyOrder(@PathVariable Long orderId){
        return mypageService.cancelMyOrder(orderId);
    }

    @GetMapping("/pickup/{memberId}/{page}")
    public List<PickupDTO> getMyPickupList(@PathVariable Long memberId, @PathVariable int page){
        return mypageService.getMyPickupList(memberId, page);
    }

    @PostMapping("/pickup/{pickupId}")
    public PickupDTO getMyPickup(@PathVariable Long pickupId){
        return mypageService.getMyPickup(pickupId);
    }

    @GetMapping("/course/{memberId}")
    public List<CourseDTO> getCourseList (@PathVariable Long memberId){
        return mypageService.getCourseList(memberId);
    }

    @GetMapping("/mycourse/{memberId}")
    public List<MycourseDTO> getMyCourseList(@PathVariable Long memberId){
        return mypageService.getMyCourseList(memberId);
    }

    @PostMapping("/course/{courseId}")
    public CourseDTO getCourse (@PathVariable Long courseId){
        return mypageService.getCourse(courseId);
    }

    @PostMapping("/badge")
    public List<BadgeDTO> getBadgeList(){
        return mypageService.getBadgeList();
    }

    @GetMapping("/badge/{memberId}")
    public List<MybadgeDTO> getMyBadgeList(@PathVariable Long memberId){
        return mypageService.getMybadgesList(memberId);
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
