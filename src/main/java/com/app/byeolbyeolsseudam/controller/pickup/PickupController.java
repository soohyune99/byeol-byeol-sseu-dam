package com.app.byeolbyeolsseudam.controller.pickup;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptCustomRepository;
import com.app.byeolbyeolsseudam.service.pickup.PickupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/pickup/*")
@RequiredArgsConstructor
@Slf4j
public class PickupController {

    private final PickupService pickupService;



    // 참고사항
    // enum PickupStatus  :  수거대기중, 신청취소, 수거중, 수거완료
    // 신청 취소는 관리자에서만 가능

    /* -------------------------------------------------- 메인 -------------------------------------------------------------------- */

    /* 기사페이지 메인 */

    @GetMapping("/main")
    public String pickMain(){
        return "/app/pickup/pickMain";
    }

    /* --------------------------------------------------- 수거대기중  ------------------------------------------------------------ */

    /* 수거 신청 목록 페이지 (상태 :  수거대기중  )  */

    @GetMapping("/wantedlist")
    public String pickWantedList(PickupDTO pickupDTO){
        return "/app/pickup/pickWantedList";
    }

    /* 수거 신청 목록 페이지 -> 수거 신청 디테일 정보 */

    @GetMapping("/detail")
    public String pickDetail(){
        return "/app/pickup/pickDetail";
    }

    /* --------------------------------------- 수거 대기중 -> 수거중  ----------------------------------------------------------------- */
    /* '수락 하기' 눌렀을 때 컨트롤러 */

    @GetMapping("/savePickup")
    public RedirectView savePickup(Long pickupId, HttpSession session){
        Pickup pickup = pickupService.updatePickupStatusIng(pickupId); // 상태를 수거중으로 변경
        Member member = (Member) session.getAttribute("member");

        PickupAccept pickupAccept = new PickupAccept();
        pickupAccept.changeMember(member);
        pickupAccept.changePickup(pickup);

        pickupService.savePickupAccept(pickupAccept);

        return new RedirectView("acceptedlist"); // 수거현황목록페이지 컨트롤러
    }


    /* -----------------------------------------------  수거중  -------------------------------------------------------------------- */

    /* 수거 현황 목록 페이지 (상태 :  수거중  )  */

    @GetMapping("/acceptedlist")
    public String pickAcceptedList(HttpSession session, Model model){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        List<PickupAcceptDTO> ingPickupDTOS = pickupService.findListPickupStatusIng(memberDTO.getMemberId());
//        log.info( "ingPickupDTOS" + ingPickupDTOS.toString());
        model.addAttribute("ingPickupDTOS", ingPickupDTOS);
        return "/app/pickup/pickAcceptedList";
    }


    /* 수거 현황 목록 페이지 -> 수거 현황 디테일 정보 */

    @GetMapping("/okdetail")
    public String pickOkDetail(Model model, Long pickupAcceptId){
        model.addAttribute("pickupAcceptedDetail",pickupService.findPickupId(pickupAcceptId));

        log.info("--------------------------------------");
        log.info("픽업아이디 : "+ pickupAcceptId);
        log.info("pickupAcceptedDetail : " + pickupService.findPickupId(pickupAcceptId));
        log.info("--------------------------------------");
        return "/app/pickup/pickOkDetail";

    }

    /* -------------------------------------------- 수거중 -> 수거 완료  ------------------------------------------------------------ */
    /* '수거완료' 눌렀을 때 컨트롤러 */

    @GetMapping("/completePickup")
    public RedirectView completePickup(Long pickupId){
        pickupService.updatePickupStatusFinish(pickupId);

        return new RedirectView("finishedlist");
    }



    /* --------------------------------------------  수거 완료  -------------------------------------------------------------------- */

    /* 수거 완료 목록 페이지 (상태 :  수거 완료  )  */

    @GetMapping("/finishedlist")
    public String pickFinishedList(HttpSession session, Model model){
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        List<PickupAcceptDTO> finishPickupDTOS = pickupService.findListPickupStatusFinish(memberDTO.getMemberId());
        model.addAttribute("finishPickupDTOS",finishPickupDTOS);
        return "/app/pickup/pickFinishedList";
    }

    /* 수거 완료 목록 페이지 -> 수거 완료 디테일 정보 */

    @GetMapping("/finisheddetail")
    public String pickFinishedDetail(Model model, Long pickupAcceptId){
        model.addAttribute("pickupFinishDetail",pickupService.findPickupId(pickupAcceptId));
        return "/app/pickup/pickFinishedDetail";
    }


}
