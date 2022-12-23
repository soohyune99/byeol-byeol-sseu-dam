package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.basket.Basket;
import com.app.byeolbyeolsseudam.service.market.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/cart/*", "/cart"})
public class BasketController {
    private final BasketService basketService;

    /* 장바구니 insert */
    @PostMapping("/new")
    public Long getBasketList(@RequestBody BasketDTO basketDTO){
        return basketService.saveBasket(basketDTO);
    }

    /* 장바구니 전체 조회 */
    @PostMapping("/show/{memberId}")
    public List<BasketDTO> showBasketList(@PathVariable Long memberId){
        return basketService.getBasket(memberId);
    }

    /* 장바구니 수량 변경 모달 상세보기*/
    @PostMapping("/modal/{basketId}")
    public BasketDTO showBasket(@PathVariable Long basketId){
        return basketService.selectBasket(basketId);
    }

    /* 장바구니 수량 변경 */
    @PostMapping("/update/{basketId}/{basketCount}")
    public void updateCount(@PathVariable Long basketId, @PathVariable int basketCount){
        basketService.updateBasket(basketId, basketCount);
    }

    /* 장바구니 상품 삭제 */
    @DeleteMapping("/delete/{basketId}")
    public void deleteBasket(@PathVariable String basketId){
        basketService.deleteBasket(basketId);
    }

    /* 장바구니 상품 한개 바로 구매*/
    @PostMapping("/buy")
    public List<BasketDTO> paymentBasket(@RequestBody String paymentFlag){
        return basketService.buyBasket(paymentFlag);
    }


}
