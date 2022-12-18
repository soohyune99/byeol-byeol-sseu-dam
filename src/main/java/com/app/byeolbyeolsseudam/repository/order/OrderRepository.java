package com.app.byeolbyeolsseudam.repository.order;

import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderCustomRepository {
    public int countByMemberMemberId(Long memberId);
    public int countByMemberMemberIdAndOrderStatusEquals(Long memberId, OrderStatus orderStatus);
}
