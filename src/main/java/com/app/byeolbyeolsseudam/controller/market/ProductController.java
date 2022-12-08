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
    @GetMapping("")
    public List<ProductDTO> getProductList() {
        return marketService.selectProducts();
    }

    // 개수 조회


    // 카테고리별 조회
    @GetMapping("/{productCategory}")
    public List<ProductDTO> getCategoryMarkets(@PathVariable ProductCategory productCategory) {
        log.info("------------------------" + productCategory);
        log.info("++++++++++++++++++" + marketService.selectProductsofCategory(productCategory));
        return marketService.selectProductsofCategory(productCategory);
    }

    // 검색
    @PostMapping("/{keyword}")
    public List<ProductDTO> getSearchProducts(@PathVariable String keyword){
        log.info("****************************" + keyword);
        return marketService.selectProductsofKeyword(keyword);
    }

    // 상세조회
    @GetMapping(value = {"/read/{productId}"})
    public ProductDTO getProductDetail(@PathVariable Long productId){
        return marketService.readProduct(productId);
    }

    // 무한 스크롤
    @PostMapping("/scroll")
    public List<ProductDTO> infiniteScroll(Criteria criteria){
        return marketService.selectScrollProducts(criteria);
    }


}
