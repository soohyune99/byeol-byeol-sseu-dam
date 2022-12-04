//package com.app.byeolbyeolsseudam.repository.review;
//
//import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
//import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
//import com.app.byeolbyeolsseudam.entity.review.QReview;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//import static com.app.byeolbyeolsseudam.entity.review.QReview.*;
//import static com.app.byeolbyeolsseudam.entity.review.QReview.review;
//
//@Repository
//@RequiredArgsConstructor
//public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
//    public final JPAQueryFactory jpaQueryFactory;
//
//    // 상품 리뷰 조회
//    @Override
//    public List<ReviewDTO> showAll(Long productId){
//        List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
//                review.reviewId, review.reviewContent, review.reviewStar,
//                review.product.productId,review.member.memberId, review.member.memberName,
//                review.reviewFileName, review.reviewFilePath, review.reviewFileUuid,
//                review.createdDate))
//                .from(review)
//                .where(review.product.productId.eq(productId))
//                .orderBy(review.createdDate.desc())
//                .fetch();
//        return reviews;
//    }
//}
