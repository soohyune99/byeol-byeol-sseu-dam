package com.app.byeolbyeolsseudam.entity.review;

import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.product.Product;
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

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeProduct(Product product){
        this.product = product;
        product.getReviews().add(this);
    }

    @Builder
    public Review(String reviewContent, double reviewStar, String reviewFileName, String reviewFilePath, String reviewFileUuid) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.reviewFileName = reviewFileName;
        this.reviewFilePath = reviewFilePath;
        this.reviewFileUuid = reviewFileUuid;
    }

    public void update(ReviewDTO reviewDTO) {
        this.reviewContent = reviewDTO.getReviewContent();
        this.reviewStar = reviewDTO.getReviewStar();
        this.reviewFileName = reviewDTO.getReviewFileName();
        this.reviewFilePath = reviewDTO.getReviewFilePath();
        this.reviewFileUuid = reviewDTO.getReviewFileUuid();
    }
}
