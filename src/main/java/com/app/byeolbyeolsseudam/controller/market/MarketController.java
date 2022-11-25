package com.app.byeolbyeolsseudam.controller.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/market/*")
public class MarketController {

    @GetMapping("/main")
    public String main(){
        return "/app/market/market";
    }

    @GetMapping("/basket")
    public String basket(){
        return "/app/market/marketBasket";
    }

    @GetMapping("/detail")
    public String detail(){
        return "/app/market/marketDetail";
    }

    @GetMapping("/payment")
    public String payment(){
        return "/app/market/marketPayment";
    }

    @GetMapping("/paid")
    public String paid(){
        return "/app/market/marketPaid";
    }
}
