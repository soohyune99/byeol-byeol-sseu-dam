package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProductDTO;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_PRODUCT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Period {
    @Id @GeneratedValue @NotNull
    private Long productId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @NotNull
    private String productName;
    @NotNull
    private int productPrice;
    @NotNull
    private int productCount;
    @NotNull
    private String productFileDetailName;
    @NotNull
    private String productFileDetailPath;
    @NotNull
    private String productFileDetailUuid;
    @NotNull
    private String productFileProfileName;
    @NotNull
    private String productFileProfilePath;
    @NotNull
    private String productFileProfileUuid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Review> reviews;

    @Builder
    public Product(ProductCategory productCategory, String productName, int productPrice, int productCount, String productFileDetailName, String productFileDetailPath, String productFileDetailUuid, String productFileProfileName, String productFileProfilePath, String productFileProfileUuid) {
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

    public void update(ProductDTO productDTO) {
        this.productCategory = productDTO.getProductCategory();
        this.productName = productDTO.getProductName();
        this.productPrice = productDTO.getProductPrice();
        this.productCount = productDTO.getProductCount();
        this.productFileDetailName = productDTO.getProductFileDetailName();
        this.productFileDetailPath = productDTO.getProductFileDetailPath();
        this.productFileDetailUuid = productDTO.getProductFileDetailUuid();
        this.productFileProfileName = productDTO.getProductFileProfileName();
        this.productFileProfilePath = productDTO.getProductFileProfilePath();
        this.productFileProfileUuid = productDTO.getProductFileProfileUuid();
    }
}
