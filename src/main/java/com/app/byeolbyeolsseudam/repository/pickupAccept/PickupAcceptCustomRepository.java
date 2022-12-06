package com.app.byeolbyeolsseudam.repository.pickupAccept;


import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PickupAcceptCustomRepository {
//  픽업 목록들
    public List<PickupDTO> findAllByPickupStatus();
//  픽업 목록 상세 조회
    public PickupDTO find(Long pickupId);

//  내가 진행중인 목록들
    public List<PickupDTO> findAllByMyPickup(Long memberId);
//  내가 완료한 목록들
    public List<PickupDTO> findAllByComplete(Long memberId);
//    픽업 수락하기 pickup 에서 -> pickupAccept
//    public void Accept(Long memberId);
//    목록들 상태 업데이트
    public void StatusUpdate(Long pickupId);
//    목록들 상태 완료 업데이트
    public void Complete(Long pickupId);
}
