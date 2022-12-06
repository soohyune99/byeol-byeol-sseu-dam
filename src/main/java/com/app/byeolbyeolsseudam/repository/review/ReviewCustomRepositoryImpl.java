package com.app.byeolbyeolsseudam.repository.review;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.QReviewDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.board.Board;
import com.app.byeolbyeolsseudam.entity.board.QBoard;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.product.Product;
import com.app.byeolbyeolsseudam.entity.product.QProduct;
import com.app.byeolbyeolsseudam.entity.review.QReview;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.product.QProduct.product;
import static com.app.byeolbyeolsseudam.entity.review.QReview.*;
import static com.app.byeolbyeolsseudam.entity.review.QReview.review;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public void saveReview(ReviewDTO reviewDTO, Review review){
        log.info("productId" + reviewDTO.getProductId());
        log.info("memberName" + reviewDTO.getMemberName());

        Product product = jpaQueryFactory.selectFrom(QProduct.product)
                .where(QProduct.product.productId.eq(reviewDTO.getProductId()))
                .fetchOne();

        review.changeProduct(product);
    }


    // 상품 리뷰 조회
    @Override
    public List<ReviewDTO> showAll(Long productId){
        List<ReviewDTO> reviews = jpaQueryFactory.select(new QReviewDTO(
                review.reviewId, review.reviewContent, review.reviewStar,
                review.product.productId,review.member.memberId, review.member.memberName,
                review.reviewFileName, review.reviewFilePath, review.reviewFileUuid,
                review.createdDate))
                .from(review)
                .where(review.product.productId.eq(productId))
                .orderBy(review.createdDate.desc())
                .fetch();
        return reviews;
    }
}
