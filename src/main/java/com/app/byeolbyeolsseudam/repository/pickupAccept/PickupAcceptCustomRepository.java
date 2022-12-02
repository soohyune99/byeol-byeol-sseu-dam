package com.app.byeolbyeolsseudam.repository.pickupAccept;


import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PickupAcceptCustomRepository {
//  픽업 목록들
    public List<PickupDTO> findAllByPickupStatus();
//  내가 진행중인 목록들
    public List<PickupDTO> findAllByMyPickup(Long memberId);
//  내가 완료한 목록들
    public List<PickupDTO> findAllByComplete(Long memberId);
//    픽업 수락하기 pickup 에서 -> pickupAccept
//    public void Accept(Long memberId);
//    목록들 상태 업데이트
    public void StatusUpdate(PickupDTO pickupDTO);
//    목록들 상태 완료 업데이트
    public void Complete(PickupDTO pickupDTO);
}
