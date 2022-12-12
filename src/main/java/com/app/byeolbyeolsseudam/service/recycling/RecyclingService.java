package com.app.byeolbyeolsseudam.service.recycling;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import org.springframework.stereotype.Service;
/* 승희꺼 */
@Service
public interface RecyclingService {

    /* 쓰담수거 _ 수거 신청 하기 */
    public void recyclingSave(PickupDTO pickupDTO);

}
