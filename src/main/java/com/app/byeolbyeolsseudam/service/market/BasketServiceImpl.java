package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.entity.basket.Basket;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.basket.BasketCustomRepository;
import com.app.byeolbyeolsseudam.repository.basket.BasketRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    @Override
    public Long saveBasket(BasketDTO basketDTO){
        BasketDTO basketDTO1 = new BasketDTO();
        Member member = memberRepository.findById(basketDTO.getMemberId()).get();
        Product product = productRepository.findById(basketDTO.getProductId()).get();
        basketDTO1.setBasketCount(basketDTO.getBasketCount());

        Basket basket = basketDTO1.toEntity();
        basket.changeMember(member);
        basket.changeProduct(product);
        basketRepository.save(basket);

        return basket.getBasketId();
    }

    @Override
    public List<BasketDTO> getBasket(Long memberId){
        return basketRepository.selectBasketList(memberId);
    }

    @Override
    public BasketDTO selectBasket(Long basketId){
        return basketRepository.selectBasket(basketId);
    }

    @Override
    public void updateBasket(Long basketId, int basketCount){
        Basket basket = basketRepository.findById(basketId).get();
        basket.updateBasketCount(basketCount);
        basketRepository.save(basket);
    }

    @Override
    public void deleteBasket(Long basketId){
        basketRepository.delete(basketRepository.findById(basketId).get());
    }
}
