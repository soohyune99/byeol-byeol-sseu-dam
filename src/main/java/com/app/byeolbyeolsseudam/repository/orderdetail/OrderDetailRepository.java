package com.app.byeolbyeolsseudam.repository.orderdetail;

import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, OrderDetailCustomRepository {
}
