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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;

    // 장바구니 저장
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

    // 장바구니 조회
    @Override
    public List<BasketDTO> getBasket(Long memberId){
        return basketRepository.selectBasketList(memberId);
    }

    // 장바구니 한개 조회
    @Override
    public BasketDTO selectBasket(Long basketId){
        return basketRepository.selectBasket(basketId);
    }

    // 장바구니 수량 변경
    @Override
    public void updateBasket(Long basketId, int basketCount){
        Basket basket = basketRepository.findById(basketId).get();
        basket.updateBasketCount(basketCount);
        basketRepository.save(basket);
    }

    // 장바구니 삭제
    @Override
    public void deleteBasket(String basketId){


        if(!basketId.contains(",")){
            basketRepository.deleteById(Long.valueOf(basketId));
        }else{
            String[] arr = basketId.split(",");

            Arrays.stream(arr).forEach(id->{
                    basketRepository.deleteById(Long.valueOf(id));
            });
        }
    }

    // 장바구니 구매
    @Override
    public List<BasketDTO> buyBasket(String basketId){
        List<BasketDTO> basketDTOO = new ArrayList<>();
        if(!basketId.contains(",")){
            String one = basketId.replaceAll("\"","");
            basketDTOO.add(basketRepository.selectBasket(Long.valueOf(one)));
        }else{
            String s = basketId.replaceAll("\"","");
            String[] arr2 = s.split(",");
            Arrays.stream(arr2).forEach(id ->{
                basketDTOO.add(basketRepository.selectBasket(Long.valueOf(id)));
            });
        }
        return basketDTOO;
    }

}
