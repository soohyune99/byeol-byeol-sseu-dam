package com.app.byeolbyeolsseudam.repository.admin;

import com.app.byeolbyeolsseudam.repository.admin.pickup.AdminPickupCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Object, Long>, AdminPickupCustomRepository {
}
