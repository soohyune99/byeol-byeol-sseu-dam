package com.app.byeolbyeolsseudam.repository.admin.orderdetail;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.entity.order.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminOrderDetailCustomRepository {
    public List<OrderDetailDTO> showOrderDetailList(Pageable pageable);
    public List<OrderDetailDTO> showDetail(Long orderId);
}
