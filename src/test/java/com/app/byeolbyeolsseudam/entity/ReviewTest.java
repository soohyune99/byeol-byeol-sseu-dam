package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.repository.review.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.review.QReview.review;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ReviewTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){
        for (int i = 0; i < 100; i++){
            ReviewDTO reviewDTO = new ReviewDTO();
            Review review = new Review();

            reviewDTO.setReviewContent("리뷰 내용" + i);
            reviewDTO.setReviewStar(i % 50);
            reviewDTO.setReviewFileName("review" + i + ".png");
            reviewDTO.setReviewFilePath("path" + i);
            reviewDTO.setReviewFileUuid("uuid" + i);
            reviewRepository.save(reviewDTO.toEntity());
            review.changeMember(memberRepository.findAll().get(i + 55));
            review.changeProduct(productRepository.findAll().get(1));
        }
    }

    // 조회
    @Test
    public void findTest(){
        List<Review> reviews = jpaQueryFactory.selectFrom(review)
                .where(review.product.productId.eq(1L))
                .fetch();

        log.info("reviewContent : " + reviews.get(0).getReviewContent());
    }

    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(review)
                .where(review.reviewId.eq(1L))
                .execute();
    }
}
