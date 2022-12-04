package com.app.byeolbyeolsseudam.domain.product;

import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.product.Product;
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
    private String productFileDetailName;
    private String productFileDetailPath;
    private String productFileDetailUuid;
    private String productFileProfileName;
    private String productFileProfilePath;
    private String productFileProfileUuid;

    private List<ReviewDTO> reviews;

    @QueryProjection
    public ProductDTO(Long productId, ProductCategory productCategory, String productName, int productPrice, int productCount, String productFileDetailName, String productFileDetailPath, String productFileDetailUuid, String productFileProfileName, String productFileProfilePath, String productFileProfileUuid) {
        this.productId = productId;
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productFileDetailName = productFileDetailName;
        this.productFileDetailPath = productFileDetailPath;
        this.productFileDetailUuid = productFileDetailUuid;
        this.productFileProfileName = productFileProfileName;
        this.productFileProfilePath = productFileProfilePath;
        this.productFileProfileUuid = productFileProfileUuid;
    }

    public Product toEntity(){
        return Product.builder()
                .productCategory(productCategory)
                .productName(productName)
                .productPrice(productPrice)
                .productCount(productCount)
                .productFileDetailName(productFileDetailName)
                .productFileDetailPath(productFileDetailPath)
                .productFileDetailUuid(productFileDetailUuid)
                .productFileProfileName(productFileProfileName)
                .productFileProfilePath(productFileProfilePath)
                .productFileProfileUuid(productFileProfileUuid)
                .build();
    }
}
