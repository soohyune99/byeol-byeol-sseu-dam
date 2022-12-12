package com.app.byeolbyeolsseudam.service.market;


import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderDetailService {

    // 주문내역 뽑기
    public ProductDTO getOrderDetailList(Long productId);
    public MemberDTO getOrderMember(Long memberId);
}
