package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProductDTO;
import com.app.byeolbyeolsseudam.repository.ProductRepository;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void saveTest(){
        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductCategory(ProductCategory.주방);
        productDTO.setProductName("천연 수세미");
        productDTO.setProductPrice(15000);
//        productDTO.getProductCount(1);
//        productDTO.getProductFileDetail("product.jpg");
//        productDTO.getProductFileProfile("profile");

        productRepository.save(productDTO.toEntity());

    }

    @Test
    public void findProduct(){
        Optional<Product> findProduct = productRepository.findById(1L);
        if(findProduct.isPresent()){
            Assertions.assertThat(findProduct.get().getProductCategory().equals("주방"));

            log.info("setProductPrice : " + findProduct.get().getProductPrice());
        }
    }

    @Test
    public void updateTest(){
        Optional<Product> updateProduct = productRepository.findById(1L);

        if(updateProduct.isPresent()){
            updateProduct.get().update(ProductCategory.반려동물, "사료", 16000,3, "tk", "df");
        }
    }

    @Test
    public void deleteTest(){
        Optional<Product> deleteProduct = productRepository.findById(1L);

        if(deleteProduct.isPresent()){
            productRepository.delete(deleteProduct.get());
        }
    }
}

