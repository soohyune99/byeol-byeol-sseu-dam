package com.app.byeolbyeolsseudam.repository.product;

import com.app.byeolbyeolsseudam.domain.Criteria;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;

import java.util.List;

public interface ProductCustomRepository {

    // 마켓 전체 조회
    public List<ProductDTO> selectProducts(Criteria criteria);

    // 마켓 상세 + 댓글 조회
    public ProductDTO readProduct(Long productId);

    // 주문하기
    public ProductDTO selectProduct(Long productId);
    public  List<ProductDTO> selectProductAll(Long productId);

}
