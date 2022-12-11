package com.app.byeolbyeolsseudam.repository.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;

import java.util.List;

public interface PickupCustomRepository {
    public List<PickupDTO> getMyPickupList(Long memberId, int page);
    public PickupDTO getMyPickup(Long pickupId);
}
