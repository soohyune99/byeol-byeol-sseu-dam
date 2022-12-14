package com.app.byeolbyeolsseudam.repository.pickupAccept;

import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupAcceptRepository extends JpaRepository<PickupAccept, Long>, PickupAcceptCustomRepository {
}
