package com.app.byeolbyeolsseudam.controller.recycling;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.service.recycling.RecyclingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/recycle/*")
@Slf4j
public class RecyclingController {
    private final RecyclingService recyclingService;

    /* 쓰담 수거 _ 처음 소개 페이지 이동 */
    @GetMapping("/recyclingintro")
    public  String recyclingIntro(){
        return "app/recycling/recyclingIntro";
    }

    /* 쓰담 수거 _ 수거 신청 목록 */
    @GetMapping("/recycling")
    public String recycling(PickupDTO pickupDTO){

        return "app/recycling/recycling";
    }

    /* 쓰담 수거 _ 신청 하기 진행 */
    @PostMapping("/recyclingOk")
    public RedirectView recyclingOk(PickupDTO pickupDTO, HttpSession session){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        Long memberId = memberDTO.getMemberId();

        recyclingService.recyclingSave(pickupDTO, memberId);
        return new RedirectView("/mypage/pickup"); // 성공시 recycling으로 이동
    }

    /* 챗봇 아이콘인듯 _______쓰담수거에서는 사용 안함 */
    @GetMapping("/question")
    public String question(){return "app/question/QuestionIntro";}


}
