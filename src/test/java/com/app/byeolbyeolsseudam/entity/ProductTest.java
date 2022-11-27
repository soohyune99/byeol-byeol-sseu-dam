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
        ProductDTO product1 = new ProductDTO();
        ProductDTO product2 = new ProductDTO();

        productDTO.setProductCategory(ProductCategory.주방);
        productDTO.setProductName("천연 수세미");
        productDTO.setProductPrice(15000);
        productDTO.setProductCount(1);
        productDTO.setProductFileDetail("product.jpg");
        productDTO.setProductFileProfile("profile");


        product1.setProductCategory(ProductCategory.반려동물);
        product1.setProductName("비건 개껌");
        product1.setProductPrice(10000);
        product1.setProductCount(80);
        product1.setProductFileDetail("food.jpg");
        product1.setProductFileProfile("food");

        product2.setProductCategory(ProductCategory.생활);
        product2.setProductName("에코백");
        product2.setProductPrice(20000);
        product2.setProductCount(100);
        product2.setProductFileDetail("living.jpg");
        product2.setProductFileProfile("living");

        productRepository.save(productDTO.toEntity());
        productRepository.save(product1.toEntity());
        productRepository.save(product2.toEntity());


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
            updateProduct.get().update(ProductCategory.반려동물, "사료", 16000,3, "사료상세정보", "사료사진");
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

