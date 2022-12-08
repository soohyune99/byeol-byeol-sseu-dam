package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.service.market.MarketService;
import com.app.byeolbyeolsseudam.service.market.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = {"/market/*", "/market"})
public class MarketController {

    private final MarketService marketService;

    @GetMapping("")
    public String main(){
        return "/app/market/market";
    }


    // 마켓 상세보기 조회
    @GetMapping("/{productId}")
    public String Read(@PathVariable Long productId, Model model){
        log.info("상세보기 들어옴");
        log.info("" + productId);
        return "/app/market/marketDetail";
    }

    /*@GetMapping(value = {"/payment"})
    public String payment(@RequestParam Long productId, @RequestParam int orderCount){
        return "/app/market/marketPayment";
    }*/

/*    @GetMapping("/basket")
    public String basket(){
        return "/app/market/marketBasket";
    }



    @GetMapping("/paid")
    public String paid(){
        return "/app/market/marketPaid";
    }*/
}
