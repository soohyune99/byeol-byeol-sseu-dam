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
    private double reviewStar;
    private Long productId;
    private Long memberId;
    private String memberName;
    private String reviewFileName;
    private String reviewFilePath;
    private String reviewFileUuid;
    private LocalDateTime createdDate;

    @QueryProjection
    public ReviewDTO(Long reviewId, String reviewContent, double reviewStar, Long productId, Long memberId, String memberName, String reviewFileName, String reviewFilePath, String reviewFileUuid, LocalDateTime createdDate) {
        this.reviewId = reviewId;
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.productId = productId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.reviewFileName = reviewFileName;
        this.reviewFilePath = reviewFilePath;
        this.reviewFileUuid = reviewFileUuid;
        this.createdDate = createdDate;
    }

    public Review toEntity(){
        return Review.builder()
                .reviewContent(reviewContent)
                .reviewStar(reviewStar)
                .reviewFileName(reviewFileName)
                .reviewFilePath(reviewFilePath)
                .reviewFileUuid(reviewFileUuid)
                .build();
    }
}
