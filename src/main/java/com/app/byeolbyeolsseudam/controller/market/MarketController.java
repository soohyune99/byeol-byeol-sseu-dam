package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.basket.BasketDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.service.market.MarketService;
import com.app.byeolbyeolsseudam.service.market.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = {"/market/*", "/market"})
public class MarketController {

    private final MarketService marketService;

    /* 마켓 페이지로 이동 */
    @GetMapping("")
    public String main(){
        return "/app/market/market";
    }


     /* 마켓 상세보기로 이동 */
    @GetMapping("/{productId}")
    public String Read(@PathVariable Long productId, Model model){
        return "/app/market/marketDetail";
    }

     /*주문하기 이동*/
    @GetMapping("/payment")
    public String payment(@RequestParam(value = "productId") Long productId, @RequestParam(value = "count") int count){
        return "/app/market/marketPayment";
    }

    @GetMapping("/basket/payment")
    public String basketpy(){
        return "/app/market/marketPayment";
    }

    @GetMapping("/paid/{orderId}")
    public String paid(@PathVariable Long orderId){
        return "/app/market/marketPaid";
    }

    @GetMapping("/basket")
    public String basket(){
        return "/app/market/marketBasket";
    }


}
