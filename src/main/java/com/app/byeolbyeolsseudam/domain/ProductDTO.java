package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Product;
import com.app.byeolbyeolsseudam.entity.Review;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class ProductDTO {
    private Long productId;
    private ProductCategory productCategory;
    private String productName;
    private int productPrice;
    private int productCount;
    private String productFileDetail;
    private String productFileProfile;

    private List<Review> reviews;

    @QueryProjection
    public ProductDTO(Long productId, ProductCategory productCategory, String productName, int productPrice, int productCount, String productFileDetail, String productFileProfile, List<Review> reviews) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productFileDetail = productFileDetail;
        this.productFileProfile = productFileProfile;
        this.reviews = reviews;
    }

    public Product toEntity(){
        return Product.builder()
                .productCategory(productCategory)
                .productName(productName)
                .productPrice(productPrice)
                .productCount(productCount)
                .productFileDetail(productFileDetail)
                .productFileProfile(productFileProfile)
                .build();
    }
}
