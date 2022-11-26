package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_REVIEW")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Period {
    @Id @GeneratedValue @NotNull
    private Long reviewId;
    @NotNull
    private String reviewContent;
    private String reviewFile;
    @NotNull
    private double reviewStar;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeProduct(Product product){
        this.product = product;
        product.getReviews().add(this);
    }

    @Builder
    public Review(String reviewContent, String reviewFile, double reviewStar, Product product, Member member) {
        this.reviewContent = reviewContent;
        this.reviewFile = reviewFile;
        this.reviewStar = reviewStar;
        this.product = product;
        this.member = member;
    }

    public void update(String reviewContent, String reviewFile, double reviewStar){
        this.reviewContent = reviewContent;
        this.reviewFile = reviewFile;
        this.reviewStar = reviewStar;
    }
}
