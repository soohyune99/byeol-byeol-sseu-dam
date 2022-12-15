package com.app.byeolbyeolsseudam.service.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PickupService {


    /* 수거 대기중 리스트 / 수거 신청페이지 _ 해당되는 지역에 따라 동적쿼리 사용*/
    /* 동적 쿼리 - 수거대기중인 상태에서 소재지 키워드 Contain 여부 확인 */
    public Page<PickupAcceptDTO> findListPickupStatusSojaeji(String searchSojaeji, Pageable pageable);

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거중 리스트 _ 수거현황 페이지 _ 기사님이 수락한 리스트 */
    public List<PickupAcceptDTO> findListPickupStatusIng(Long memberId);


    /* 수거완료 리스트 _ 수거완료 페이지 _ 기사님이 수거 완료한 리스트 */
    public List<PickupAcceptDTO> findListPickupStatusFinish(Long memberId);

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거 대기중,수거중,수거완료 _ 상세 페이지 이동 / 클릭시 디테일 이동( 픽업엡셋아이디로 조회 필요 신청하는 순간에는 기사님 멤버아이디(세션에있는것) 가져오기 */
    public PickupAcceptDTO findPickupId(Long pickupAcceptId);

    /*-----------------------------------------------------------------------------------------------*/

    /* 수거대기중 상세페이지에서 _ 수락하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기 */
    public Pickup updatePickupStatusIng(Long pickupId);

    /* 수거중 상세페이지에서 _ 수거완료하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기*/
    public void updatePickupStatusFinish(Long pickupId);

    //  =>  픽업엔티티에 update메소드가 있기 때문에 서비스 에서 사용 후 컨트롤러에서 사용하면됨. 따로 만들필요 없음.

    /*-----------------------------------------------------------------------------------------------*/



    public void savePickupAccept(PickupAccept pickupAccept);





}

