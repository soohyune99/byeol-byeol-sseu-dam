package com.app.byeolbyeolsseudam.repository.admin.product;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminProductCustomRepository {
//    public List<ProductDTO> showProductList();

    public List<ProductDTO> searchProduct(String keyword);
    public List<ProductDTO> searchProductPaging(String keyword, Pageable pageable);
    public ProductDTO selectById(Long productId);
    public void update(ProductDTO productDTO);
}
