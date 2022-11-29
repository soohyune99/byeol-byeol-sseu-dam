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
    @NotNull
    private double reviewStar;
    private String reviewFileName;
    private String reviewFilePath;
    private String reviewFileUuid;
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
    public Review(String reviewContent, double reviewStar, String reviewFileName, String reviewFilePath, String reviewFileUuid, Product product, Member member) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewFileName = reviewFileName;
        this.reviewFilePath = reviewFilePath;
        this.reviewFileUuid = reviewFileUuid;
        this.product = product;
        this.member = member;
    }

    public void update(String reviewContent, double reviewStar, String reviewFileName, String reviewFilePath, String reviewFileUuid) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewFileName = reviewFileName;
        this.reviewFilePath = reviewFilePath;
        this.reviewFileUuid = reviewFileUuid;
    }
}
