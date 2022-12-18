package com.app.byeolbyeolsseudam.repository.basket;

import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.entity.basket.Basket;

import java.util.List;

public interface BasketCustomRepository {

    // 장바구니 전체 조회
    public List<BasketDTO> selectBasketList(Long memberId);

    // 장바구니 한개 조회
    public BasketDTO selectBasket(Long basketId);

    // 장바구니 선택 구매
//    public BasketDTO paymentBasket(Long basketId);


}
