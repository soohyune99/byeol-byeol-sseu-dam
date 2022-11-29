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
        productDTO.setProductFileDetailName("file.png");
        productDTO.setProductFileDetailPath("path1");
        productDTO.setProductFileDetailUuid("uuid1");
        productDTO.setProductFileProfileName("profile.png");
        productDTO.setProductFileProfilePath("path11");
        productDTO.setProductFileProfileUuid("uuid11");

        product1.setProductCategory(ProductCategory.반려동물);
        product1.setProductName("비건 개껌");
        product1.setProductPrice(10000);
        product1.setProductCount(80);
        product1.setProductFileDetailName("file2.png");
        product1.setProductFileDetailPath("path2");
        product1.setProductFileDetailUuid("uuid2");
        product1.setProductFileProfileName("profile2.png");
        product1.setProductFileProfilePath("path22");
        product1.setProductFileProfileUuid("uuid22");

        product2.setProductCategory(ProductCategory.생활);
        product2.setProductName("에코백");
        product2.setProductPrice(20000);
        product2.setProductCount(100);
        product2.setProductFileDetailName("file3.png");
        product2.setProductFileDetailPath("path3");
        product2.setProductFileDetailUuid("uuid3");
        product2.setProductFileProfileName("profile3.png");
        product2.setProductFileProfilePath("path33");
        product2.setProductFileProfileUuid("uuid33");

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
            updateProduct.get().update(ProductCategory.반려동물,
                    "개껌", 25000, 100, "file.png",
                    "pathchange", "uuidchange", "profile",
                    "profilepath", "profileuuidchange");
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

