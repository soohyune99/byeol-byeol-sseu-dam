package com.app.byeolbyeolsseudam.service.market;


import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BasketService {

    // 장바구니 저장
    public Long saveBasket(BasketDTO basketDTO);

    // 장바구니 조회
    public List<BasketDTO> getBasket(Long memberId);

    // 장바구니 한개 조회
    public BasketDTO selectBasket(Long basketId);

    // 장바구니 수량 변경
    public void updateBasket(Long basketId, int basketCount);

    // 장바구니 삭제
    public void deleteBasket(Long basketId);

}
