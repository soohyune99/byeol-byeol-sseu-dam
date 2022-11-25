package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_PRODUCT")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends Period {
    @Id @GeneratedValue
    private Long productId;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    private String productName;
    private int productPrice;
    private int productCount;
    private String productFileDetail;
    private String productFileProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    private List<Review> reviews;
}
