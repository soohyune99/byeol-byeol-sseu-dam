package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final ProductRepository productRepository;

    // 상품 전체 조회
    @Override
    public List<ProductDTO> selectProducts(Criteria criteria){
        return productRepository.selectProducts(criteria);
    }

    // 상품 상세 조회
    @Override
    public ProductDTO readProduct(Long productId){
        return productRepository.readProduct(productId);
    }

}
