package com.app.byeolbyeolsseudam.repository.admin.spot;

import com.app.byeolbyeolsseudam.entity.spot.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminSpotRepository extends JpaRepository<Spot, Long>, AdminSpotCustomRepository {
}
