package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

//    private final OrderDetailRepository orderDetailRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public ProductDTO getOrderDetailList(Long productId){
        return productRepository.selectProduct(productId);
    }

    @Override
    public MemberDTO getOrderMember(Long memberId){
        return memberRepository.selectMember(memberId);
    }


}
