package com.app.byeolbyeolsseudam.repository.admin.orderdetail;

import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderDetailRepository extends JpaRepository<OrderDetail, Long>, AdminOrderDetailCustomRepository {
}
