package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Product;
import com.app.byeolbyeolsseudam.entity.Review;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ReviewDTO {
    private Long reviewId;
    private String reviewContent;
    private String reviewFile;
    private double reviewStar;
    private Product product;
    private Member member;
    private LocalDateTime createdDate;

    @QueryProjection
    public ReviewDTO(Long reviewId, String reviewContent, String reviewFile, double reviewStar, Product product, Member member, LocalDateTime createdDate) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewFile = reviewFile;
        this.reviewStar = reviewStar;
        this.product = product;
        this.member = member;
        this.createdDate = createdDate;
    }

    public Review toEntity(){
        return Review.builder()
                .reviewContent(reviewContent)
                .reviewFile(reviewFile)
                .reviewStar(reviewStar)
                .product(product)
                .member(member)
                .build();
    }
}
