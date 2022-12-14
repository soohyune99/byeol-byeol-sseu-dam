//package com.app.byeolbyeolsseudam.service.pickup;
//
//import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
//import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
//import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
//import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
//import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
//import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
//import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptCustomRepository;
//import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class PickupService2 {
//    private final PickupAcceptCustomRepository pickupAcceptCustomRepository;
//    private final PickupAcceptRepository pickupAcceptRepository;
//    private final PickupRepository pickupRepository;
//    private final MemberRepository memberRepository;
//
////    //    픽업 목록들
////    public List<PickupDTO> getPickupList(){
////        return pickupAcceptCustomRepository.findAllByPickupStatus();
////    }
////
////    //    내가 진행중인 목록들
////    public List<PickupDTO> getMyPickupList(Long memberId){
////        return pickupAcceptCustomRepository.findAllByMyPickup(memberId);
////    }
////    //   내가 완료한 목록들
////    public List<PickupDTO> getMyCompleteList(Long memberId){
////        return pickupAcceptCustomRepository.findAllByComplete(memberId);
////    }
////
////    //  선택한 목록 수거중으로 업데이트 하고, 픽업accept에 세이브하세요
////    public void UpdateAndSave(Long pickupId, Long memberId){
////        Pickup pickup = pickupRepository.findById(pickupId).get();
////
////        pickupAcceptCustomRepository.StatusUpdate(pickup.getPickupId());
////
////        PickupAccept pickupAccept = new PickupAccept();
////
////        pickupAccept.changeMember(memberRepository.findById(memberId).get());
////        pickupAccept.changePickup(pickupRepository.findById(pickupId).get());
////
////        pickupAcceptRepository.save(pickupAccept);
////
////    }
////
////    //  선택한 목록을 수거완료로 업데이트하고, 픽업accept에 세이브하세요
////    public void CompleteAndSave(Long pickupId){
////        pickupAcceptCustomRepository.Complete(pickupId);
////
////    }
//}
