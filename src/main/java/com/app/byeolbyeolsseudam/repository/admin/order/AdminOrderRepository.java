package com.app.byeolbyeolsseudam.repository.admin.order;

import com.app.byeolbyeolsseudam.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderRepository extends JpaRepository<Order, Long>, AdminOrderCustomRepository {
}
