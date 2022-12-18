package com.app.byeolbyeolsseudam.repository.pickup;

import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupRepository extends JpaRepository<Pickup, Long>, PickupCustomRepository {
    public int countByMemberMemberId(Long memberId);
}
