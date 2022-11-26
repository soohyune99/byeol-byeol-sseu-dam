package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Pickup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PickupRepository extends JpaRepository<Pickup, Long> {
}
