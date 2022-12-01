package com.app.byeolbyeolsseudam.repository.pickupAccept;


import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;

import java.util.List;

public interface PickupAcceptCustomRepository {
    public List<PickupDTO> findAllByPickupStatus();
    public List<PickupDTO> findAllByMyPickup(Long memberId);
    public List<PickupDTO> findAllByComplete(Long memberId);
//    public Page<PickupDTO> findByPickupName(Pageable pageable);
}
