package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.service.market.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/ordering/*", "/ordering"})
public class OrderController {
    private final OrderService orderService;

    // 주문 상세 조회
    @GetMapping("/{productId}")
    public ProductDTO getProductList(@PathVariable Long productId){
        return orderService.getOrderDetailList(productId);
    }

    @PostMapping("/{memberId}")
    public MemberDTO getMemberList(@PathVariable Long memberId){
        return orderService.getOrderMember(memberId);
    }

    @PostMapping(value = "/payment", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public void saveOrderList(@PathVariable int priceAmount){
        log.info("=========================orderController============================");
    }

}
