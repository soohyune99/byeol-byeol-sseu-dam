package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProductDTO;
import com.app.byeolbyeolsseudam.domain.ReviewDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.ProductRepository;
import com.app.byeolbyeolsseudam.repository.ReviewRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Test
    public void saveTest(){
        ReviewDTO review = new ReviewDTO();

        review.setReviewContent("리뷰 내용1");
        review.setReviewFile("review.file");
        review.setReviewStar(5.1);
        review.setMember(memberRepository.findAll().get(1));
        review.setProduct(productRepository.findAll().get(1));

        reviewRepository.save(review.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Review> findReview = reviewRepository.findById(15L);

        if(findReview.isPresent()){
            Assertions.assertThat(findReview.get().getMember().getMemberName().equals("은지"));

            log.info("ReviewContent : " + findReview.get().getReviewContent());
        }
    }

    @Test
    public void updateTest(){
        Optional<Review> updateReview = reviewRepository.findById(15L);

        if(updateReview.isPresent()){
            updateReview.get().update("내용 수정1", "파일 수정1", 5.6);
        }
    }

    @Test
    public void deleteTest(){
        Optional<Review> deleteReview = reviewRepository.findById(15L);

        if(deleteReview.isPresent()){
            Assertions.assertThat(deleteReview.get().getMember().getMemberName().equals("은지"));

            log.info("ReviewContent : " + deleteReview.get().getReviewContent());
        }
    }
}
