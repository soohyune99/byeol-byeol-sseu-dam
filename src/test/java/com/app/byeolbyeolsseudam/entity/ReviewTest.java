package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProductDTO;
import com.app.byeolbyeolsseudam.domain.ReviewDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.ProductRepository;
import com.app.byeolbyeolsseudam.repository.ReviewRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.QBasket.basket;
import static com.app.byeolbyeolsseudam.entity.QReview.review;

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
        ReviewDTO reviewDTO = new ReviewDTO();
        Review review = new Review();

        reviewDTO.setReviewContent("리뷰 내용5");
        reviewDTO.setReviewStar(5.1);
        reviewDTO.setReviewFileName("review.png");
        reviewDTO.setReviewFilePath("path1");
        reviewDTO.setReviewFileUuid("uuid1");

        reviewRepository.save(reviewDTO.toEntity());
        review.changeMember(memberRepository.findAll().get(0));
        review.changeProduct(productRepository.findAll().get(0));


    }

//    @Test
//    public void findTest(){
//        Optional<Review> findReview = reviewRepository.findById(6L);
//
//        if(findReview.isPresent()){
//            Assertions.assertThat(findReview.get().getMember().getMemberName().equals("홍수현"));
//
//            log.info("ReviewContent : " + findReview.get().getReviewContent());
//        }
//    }
//


    @Test
    public void deleteTest(){
        jpaQueryFactory.delete(review)
                .where(review.reviewId.eq(1L))
                .execute();
    }
}
