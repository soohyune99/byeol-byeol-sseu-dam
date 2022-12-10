package com.app.byeolbyeolsseudam.repository.review;

import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.review.QReview.review;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewDTO> getReviewList(Long productId){
        return jpaQueryFactory.select(new QReviewDTO(
                review.reviewId, review.reviewContent, review.reviewStar,
                review.product.productId,review.member.memberId,
                review.member.memberName,review.reviewFileName,
                review.reviewFilePath, review.reviewFileUuid,
                review.createdDate))
                .from(review)
                .where(review.product.productId.eq(productId))
                .orderBy(review.createdDate.desc())
                .limit(5)
                .fetch();

    }

    @Override
    public List<ReviewDTO> getMoreReview(Long productId, int page){
        return jpaQueryFactory.select(new QReviewDTO(
                review.reviewId, review.reviewContent, review.reviewStar,
                review.product.productId,review.member.memberId,
                review.member.memberName,review.reviewFileName,
                review.reviewFilePath, review.reviewFileUuid,
                review.createdDate))
                .from(review)
                .where(review.product.productId.eq(productId))
                .orderBy(review.createdDate.desc())
                .offset(page * 5)
                .limit(5)
                .fetch();
    }
}
