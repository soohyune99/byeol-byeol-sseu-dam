package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Basket;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Product;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BasketDTO {
    private Long basketId;
    private int basketCount;
    private String memberId;
    private Long productId;
    private int productPrice;
    private String productName;
    private ProductCategory productCategory;
    private String productFileDetailName;
    private String productFileDetailPath;
    private String productFileDetailUuid;
    private String productFileProfileName;
    private String productFileProfilePath;
    private String productFileProfileUuid;

    @QueryProjection
    public BasketDTO(Long basketId, String memberId, Long productId, ProductCategory productCategory, String productName, int productPrice, String productFileDetailName, String productFileDetailPath, String productFileDetailUuid, String productFileProfileName, String productFileProfilePath, String productFileProfileUuid) {
        this.basketId = basketId;
        this.memberId = memberId;
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productFileDetailName = productFileDetailName;
        this.productFileDetailPath = productFileDetailPath;
        this.productFileDetailUuid = productFileDetailUuid;
        this.productFileProfileName = productFileProfileName;
        this.productFileProfilePath = productFileProfilePath;
        this.productFileProfileUuid = productFileProfileUuid;
    }

    public Basket toEntity(Member member, Product product){
        return Basket.builder()
                .member(member)
                .product(product)
                .build();
    }
}
