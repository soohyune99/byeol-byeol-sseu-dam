package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.service.market.MarketService;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/product/*", "/product"})
public class ProductController {
    private final MarketService marketService;

    // 전체 조회
    @PostMapping("")
    public List<ProductDTO> getProductList(Criteria criteria) {
        return marketService.selectProducts(criteria);
    }

    // 상세조회
    @GetMapping("/read/{productId}")
    public ProductDTO getProductDetail(@PathVariable Long productId){
        return marketService.readProduct(productId);
    }

}
