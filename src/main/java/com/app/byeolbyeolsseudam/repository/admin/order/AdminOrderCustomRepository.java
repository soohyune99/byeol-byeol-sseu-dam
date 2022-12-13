package com.app.byeolbyeolsseudam.repository.admin.order;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminOrderCustomRepository {
    public List<OrderDTO> showOrderList(Pageable pageable);
    public void update(OrderDTO orderDTO);
    public OrderDTO showOrderDetail(Long orderId);
}
