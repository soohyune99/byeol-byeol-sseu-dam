package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Test
    public void saveTest(){

        for (int i = 0; i <20; i++){
            ProductDTO productDTO = new ProductDTO();
            ProductDTO product1 = new ProductDTO();
            ProductDTO product2 = new ProductDTO();
            ProductDTO product3 = new ProductDTO();
            ProductDTO product4 = new ProductDTO();
            ProductDTO product5 = new ProductDTO();
            ProductDTO product6 = new ProductDTO();

            productDTO.setProductCategory(ProductCategory.주방);
            productDTO.setProductName("천연 수세미" + i);
            productDTO.setProductPrice(15000);
            productDTO.setProductCount(i + 1);
            productDTO.setProductFileDetailName("file.png"+ i);
            productDTO.setProductFileDetailPath("path1"+ i);
            productDTO.setProductFileDetailUuid("uuid1"+ i);
            productDTO.setProductFileProfileName("profile.png"+ i);
            productDTO.setProductFileProfilePath("path11"+ i);
            productDTO.setProductFileProfileUuid("uuid11"+ i);

            product1.setProductCategory(ProductCategory.반려동물);
            product1.setProductName("비건 개껌"+ i);
            product1.setProductPrice(10000);
            product1.setProductCount(i + 1);
            product1.setProductFileDetailName("file2.png"+ i);
            product1.setProductFileDetailPath("path2"+ i);
            product1.setProductFileDetailUuid("uuid2"+ i);
            product1.setProductFileProfileName("profile2.png"+ i);
            product1.setProductFileProfilePath("path22"+ i);
            product1.setProductFileProfileUuid("uuid22"+ i);

            product2.setProductCategory(ProductCategory.생활);
            product2.setProductName("에코백"+ i);
            product2.setProductPrice(20000);
            product2.setProductCount(i + 1);
            product2.setProductFileDetailName("file.png"+ i);
            product2.setProductFileDetailPath("path3"+ i);
            product2.setProductFileDetailUuid("uuid3"+ i);
            product2.setProductFileProfileName("profile3.png"+ i);
            product2.setProductFileProfilePath("path33"+ i);
            product2.setProductFileProfileUuid("uuid33"+ i);

            product3.setProductCategory(ProductCategory.문구);
            product3.setProductName("연필"+ i);
            product3.setProductPrice(20000);
            product3.setProductCount(i + 1);
            product3.setProductFileDetailName("file.png"+ i);
            product3.setProductFileDetailPath("path"+ i);
            product3.setProductFileDetailUuid("uuid"+ i);
            product3.setProductFileProfileName("profile.png"+ i);
            product3.setProductFileProfilePath("path"+ i);
            product3.setProductFileProfileUuid("uuid"+ i);

            product4.setProductCategory(ProductCategory.욕실);
            product4.setProductName("바디워시"+ i);
            product4.setProductPrice(10000);
            product4.setProductCount(i + 1);
            product4.setProductFileDetailName("file.png"+ i);
            product4.setProductFileDetailPath("path"+ i);
            product4.setProductFileDetailUuid("uuid"+ i);
            product4.setProductFileProfileName("profile.png"+ i);
            product4.setProductFileProfilePath("path"+ i);
            product4.setProductFileProfileUuid("uuid"+ i);

            product5.setProductCategory(ProductCategory.식품);
            product5.setProductName("비건식품"+ i);
            product5.setProductPrice(30000);
            product5.setProductCount(i + 1);
            product5.setProductFileDetailName("file.png"+ i);
            product5.setProductFileDetailPath("path"+ i);
            product5.setProductFileDetailUuid("uuid"+ i);
            product5.setProductFileProfileName("profile.png"+ i);
            product5.setProductFileProfilePath("path"+ i);
            product5.setProductFileProfileUuid("uuid"+ i);

            product6.setProductCategory(ProductCategory.취미);
            product6.setProductName("공예"+ i);
            product6.setProductPrice(50000);
            product6.setProductCount(i + 1);
            product6.setProductFileDetailName("file.png"+ i);
            product6.setProductFileDetailPath("path"+ i);
            product6.setProductFileDetailUuid("uuid"+ i);
            product6.setProductFileProfileName("profile.png"+ i);
            product6.setProductFileProfilePath("path"+ i);
            product6.setProductFileProfileUuid("uuid"+ i);

            productRepository.save(productDTO.toEntity());
            productRepository.save(product1.toEntity());
            productRepository.save(product2.toEntity());
            productRepository.save(product3.toEntity());
            productRepository.save(product4.toEntity());
            productRepository.save(product5.toEntity());
            productRepository.save(product6.toEntity());
        }


    }

    @Test
    public void findProduct(){
        Optional<Product> findProduct = productRepository.findById(1L);
        if(findProduct.isPresent()){
            Assertions.assertThat(findProduct.get().getProductCategory().equals("주방"));

            log.info("setProductPrice : " + findProduct.get().getProductPrice());
        }
    }

    // 리스트 조회
    @Test
    public void searchTest(){
        List<Product> products =  jpaQueryFactory.selectFrom(product)
                .where(product.productCategory.eq(ProductCategory.생활))
                .orderBy(product.productId.desc())
                .limit(1)
                .fetch();

        log.info("productName : " + products.get(0).getProductName());
        log.info("productPrice :" + products.get(0).getProductPrice());
    }

    @Test
    public void deleteTest(){
        Optional<Product> deleteProduct = productRepository.findById(1L);

        if(deleteProduct.isPresent()){
            productRepository.delete(deleteProduct.get());
        }
    }
}

