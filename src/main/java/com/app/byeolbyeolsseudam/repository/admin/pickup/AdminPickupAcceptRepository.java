package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPickupAcceptRepository extends JpaRepository<PickupAccept, Long>,AdminPickupAcceptCustomRepository {
}
