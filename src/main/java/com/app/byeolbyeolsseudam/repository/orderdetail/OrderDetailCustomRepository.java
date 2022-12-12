package com.app.byeolbyeolsseudam.repository.orderdetail;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;

import java.util.List;

public interface OrderDetailCustomRepository {

    // 주문 내역 뽑기
    public OrderDetailDTO showOrderDetail(Long productId);


}
