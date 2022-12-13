package com.app.byeolbyeolsseudam.service.market;


import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {

    // 주문 관련 상품 조회
    public ProductDTO getOrderDetailList(Long productId);

    // 회원 조회
    public MemberDTO getOrderMember(Long memberId);

    // 주문
}
