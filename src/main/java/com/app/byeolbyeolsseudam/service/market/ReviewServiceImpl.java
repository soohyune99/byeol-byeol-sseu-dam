package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review saveReview(ReviewDTO reviewDTO){
        Review review = reviewDTO.toEntity();
        reviewRepository.save(review);
        return review;
    }

    @Override
    public void updateReview(ReviewDTO reviewDTO, Review review){
        reviewRepository.saveReview(reviewDTO, review);
    }

    // 상품 리뷰 조회
    @Override
    public List<ReviewDTO> showReview(Long productId){
        return reviewRepository.showAll(productId);
    }



}
