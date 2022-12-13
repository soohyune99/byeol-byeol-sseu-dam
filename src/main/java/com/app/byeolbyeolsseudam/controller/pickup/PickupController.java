package com.app.byeolbyeolsseudam.controller.pickup;

import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.service.pickup.PickupService2;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/pickup/*")
@RequiredArgsConstructor
public class PickupController {
    // 참고사항
    // enum PickupStatus  :  수거대기중, 신청취소, 수거중, 수거완료
    // 신청 취소는 관리자에서만 가능

    private final PickupService2 pickupService2;
    /* -------------------------------------------------- 메인 -------------------------------------------------------------- */

    /* 기사페이지 메인 */

    @GetMapping("/main")
    public String pickMain(){
        return "/app/pickup/pickMain";
    }
    /* --------------------------------------------------- 수거대기중  ------------------------------------------------------------- */

    /* 수거 신청 목록 페이지 (상태 :  수거대기중  )  */

    @GetMapping("/wantedlist")
    public String pickWantedList(Model model, Pageable pageable){
//        model.addAttribute("pickups", pickupService2.getPickupList());
//        Page<PickupDTO> pickupList = pickupService2.getPickupList3(pageable);
//        model.addAttribute("pickupList", pickupList);
//
//        log.debug("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}",
//                boardList.getTotalElements(), boardList.getTotalPages(), boardList.getSize(),
//                boardList.getNumber(), boardList.getNumberOfElements());

        return "/app/pickup/pickWantedList";
    }

    /* 수거신청 목록 페이지 -> 수거 신청 디테일 정보 */

    @GetMapping("/detail")
    public String pickDetail(){
        return "/app/pickup/pickDetail";
    }

    /* -----------------------------------------------  수거중  ----------------------------------------------------------------- */

    /* 수거 현황 목록 페이지 (상태 :  수거중  )  */

    @GetMapping("/acceptedlist")
    public String pickAcceptedList(){
        return "/app/pickup/pickAcceptedList";
    }


    /* 수거 현황 목록 페이지 -> 수거 현황 디테일 정보 */

    @GetMapping("/okdetail")
    public String pickOkDetail(){
        return "/app/pickup/pickOkDetail";
    }

    /* --------------------------------------------  수거완료  -------------------------------------------------------------------- */

    /* 수거 완료 목록 페이지 (상태 :  수거완료  )  */

    @GetMapping("/finishedlist")
    public String pickFinishedList(){
        return "/app/pickup/pickFinishedList";
    }

    /* 수거 완료 목록 페이지 -> 수거 완료 디테일 정보 */

    @GetMapping("/finisheddetail")
    public String pickFinishedDetail(){
        return "/app/pickup/pickFinishedDetail";
    }


}
