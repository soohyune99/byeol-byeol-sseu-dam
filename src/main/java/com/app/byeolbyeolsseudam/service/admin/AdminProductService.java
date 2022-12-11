package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.notice.NoticeDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.notice.Notice;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.admin.product.AdminProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminProductService {
    private final AdminProductRepository adminProductRepository;

    public Page<ProductDTO> searchProduct(Pageable pageable){
       List<ProductDTO> products = adminProductRepository.searchProductPaging("",pageable);

       Page<ProductDTO> productList = new PageImpl<>(products,pageable,adminProductRepository.findAll().size());
       return productList;
    }

    public Page<ProductDTO> searchProductPaging(String keyword, Pageable pageable){

        List<ProductDTO> search = adminProductRepository.searchProduct(keyword);
        List<ProductDTO> products = adminProductRepository.searchProductPaging(keyword, pageable);

        Page<ProductDTO> productSearchList = new PageImpl<>(products,pageable,search.size());
        return productSearchList;

    }

    public void saveProduct(ProductDTO productDTO){
        adminProductRepository.save(productDTO.toEntity());
    }

    public void removeProduct(List<String> productIdstr){
        List<Long> productId = new ArrayList<>();
        productIdstr.stream().map(Long::parseLong).forEach(productId::add);
        productId.forEach(adminProductRepository::deleteById);
    }

    public ProductDTO selectById(Long productId){
        return adminProductRepository.selectById(productId);
    }

    public void updateProduct(ProductDTO productDTO, Long productId){
        adminProductRepository.update(productDTO);
        Product product = adminProductRepository.findById(productId).get();
        adminProductRepository.save(product);
    }
}
