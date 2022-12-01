package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
