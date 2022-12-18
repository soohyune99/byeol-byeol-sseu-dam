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

    @Override
    public List<BasketDTO> buyBasket(String basketId){
        log.info("==============" + basketId);
        List<BasketDTO> basketDTOO = new ArrayList<>();
        if(!basketId.contains(",")){
            String one = basketId.replaceAll("\"","");
            log.info("========111111======" + one);
            basketDTOO.add(basketRepository.selectBasket(Long.valueOf(one)));
        }else{
            log.info("======2222222========" + basketId);
            String s = basketId.replaceAll("\"","");
            log.info("-------------------" + s);
            String[] arr2 = s.split(",");
            log.info("======3333333========" + arr2);
            Arrays.stream(arr2).forEach(id ->{
                log.info("======4444444========" + id);
                basketDTOO.add(basketRepository.selectBasket(Long.valueOf(id)));
            });
        }
        basketDTOO.forEach(basket -> {
            log.info("=========================" + basket.getProductName());
        });
        return basketDTOO;
//        basketId.stream().map(basketRepository::selectBasket).forEach(basketDTOO::add);
//        return basketDTOO;
    }

}
