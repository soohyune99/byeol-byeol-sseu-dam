package com.app.byeolbyeolsseudam.entity;

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
    private String productFileDetail;
    @NotNull
    private String productFileProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Review> reviews;

    @Builder
    public Product(ProductCategory productCategory, String productName, int productPrice, int productCount, String productFileDetail, String productFileProfile) {
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productFileDetail = productFileDetail;
        this.productFileProfile = productFileProfile;
    }

    public void update(ProductCategory productCategory, String productName, int productPrice, int productCount, String productFileDetail, String productFileProfile){
        this.productCategory = productCategory;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCount = productCount;
        this.productFileDetail = productFileDetail;
        this.productFileProfile = productFileProfile;
    }
}
