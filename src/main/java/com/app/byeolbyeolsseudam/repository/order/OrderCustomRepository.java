package com.app.byeolbyeolsseudam.repository.order;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;

import java.util.List;

public interface OrderCustomRepository {
    public List<OrderDTO> selectMyOrderList(Long memberId, int page);
    public List<OrderDTO> selectMyCancelList(Long memberId, int page);
    public OrderDTO selectMyOrder(Long orderId);
}
