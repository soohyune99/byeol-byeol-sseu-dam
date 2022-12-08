package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.service.market.MarketService;
import com.app.byeolbyeolsseudam.service.market.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
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
        return "/app/market/marketDetail";
    }

/*    @GetMapping("/basket")
    public String basket(){
        return "/app/market/marketBasket";
    }


    @PostMapping("/payment")
    public String payment(@RequestParam Long productId, @RequestParam int orderCount,Model model){
        model.addAttribute("product", marketService.showListDetailOnly(productId));
        model.addAttribute("productPrice", marketService.showListDetailOnly(productId).getProductPrice());
//        model.addAttribute("product", product);
        model.addAttribute("count", orderCount);

        return "/app/market/marketPayment";
    }

    @GetMapping("/paid")
    public String paid(){
        return "/app/market/marketPaid";
    }*/
}
