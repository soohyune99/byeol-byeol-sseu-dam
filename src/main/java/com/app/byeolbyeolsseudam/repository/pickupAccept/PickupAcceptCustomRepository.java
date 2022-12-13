package com.app.byeolbyeolsseudam.repository.pickupAccept;


import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;

import java.util.List;

public interface PickupAcceptCustomRepository {

    /* 수거 대기중 리스트 / 수거 신청페이지 _ 해당되는 지역에 따라 동적쿼리 사용*/
    /* 동적 쿼리 - 수거대기중인 상태에서 소재지 키워드 Contain 여부 확인 */
    public List<PickupDTO> findListPickupStatusSojaeji(String sojaeji);

    /* 수거중 리스트 _ 수거현황 페이지 _ 기사님이 수락한 리스트 */
    public List<PickupDTO> findListPickupStatusIng(Long memberId);


    /* 수거완료 리스트 _ 수거완료 페이지 _ 기사님이 수거 완료한 리스트 */
    public List<PickupDTO> findListPickupStatusFinish(Long memberId);

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거 대기중,수거중,수거완료 _ 상세 페이지 이동 / 클릭시 디테일 이동( 픽업엡셋아이디로 조회 필요 신청하는 순간에는 기사님 멤버아이디(세션에있는것) 가져오기 */
    public PickupDTO findPickupId(Long pickupId);

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거대기중 상세페이지에서 _ 수락하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기 */
    public void savePickupAccept(Long pickupId, Long memberId);

    /* 수거중 상세페이지에서 _ 수거완료하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기*/
    public void CompletePickup(Long pickupId, Long memberId);

    /*-----------------------------------------------------------------------------------------------*/
    /*-----------------------------------------------------------------------------------------------*/

//    //  픽업 목록들 _ 상태를 기준으로 분류 진행
//    public List<PickupDTO> findAllByPickupStatus();
//
//    //  픽업 목록 상세 조회
//    public PickupDTO find(Long pickupId);
//
//    //  내가 진행중인 목록들
//    public List<PickupDTO> findAllByMyPickup(Long memberId);
//    //  내가 완료한 목록들
//    public List<PickupDTO> findAllByComplete(Long memberId);
//    //    픽업 수락하기 pickup 에서 -> pickupAccept
////    public void Accept(Long memberId);
////    목록들 상태 업데이트
//    public void StatusUpdate(Long pickupId);
//    //    목록들 상태 완료 업데이트
//    public void Complete(Long pickupId);
}
