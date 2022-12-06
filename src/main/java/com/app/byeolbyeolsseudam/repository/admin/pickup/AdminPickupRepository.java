package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminPickupRepository extends JpaRepository<Pickup, Long>, AdminPickupCustomRepository {
}
