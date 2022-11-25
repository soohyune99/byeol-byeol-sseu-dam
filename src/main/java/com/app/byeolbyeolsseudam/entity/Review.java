package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REVIEW")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Period {
    @Id @GeneratedValue
    private Long reviewId;
    private String reviewContent;
    private String reviewFile;
    private double reviewStar;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeProduct(Product product){
        this.product = product;
        product.getReviews().add(this);
    }
}
