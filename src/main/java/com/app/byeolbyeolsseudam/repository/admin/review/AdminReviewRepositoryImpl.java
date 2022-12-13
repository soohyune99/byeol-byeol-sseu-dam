package com.app.byeolbyeolsseudam.repository.admin.review;

import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.review.QReview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.review.QReview.review;

@Repository
@RequiredArgsConstructor
public class AdminReviewRepositoryImpl implements AdminReviewCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ReviewDTO> showReviewList(Pageable pageable) {
        return jpaQueryFactory.select(new QReviewDTO(
                review.reviewId,
                review.reviewContent,
                review.reviewStar,
                review.product.productId,
                review.member.memberId,
                review.member.memberName,
                review.reviewFileName,
                review.reviewFilePath,
                review.reviewFileUuid,
                review.createdDate
        )).from(review)
                .orderBy(review.reviewId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }


}
