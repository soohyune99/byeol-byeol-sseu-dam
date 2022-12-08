package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarketService {

    // 상품 전체 조회
    public List<ProductDTO> selectProducts();

    // 상품 카테고리별 조회
    public List<ProductDTO> selectProductsofCategory(ProductCategory productCategory);

    // 상품 검색
    public List<ProductDTO> selectProductsofKeyword(String keyword);

    // 상품 상세 조회
    public ProductDTO readProduct(Long productId);

    // 무한 스크롤
    public List<ProductDTO> selectScrollProducts(Criteria criteria);

}
