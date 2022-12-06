package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public Review saveReview(ReviewDTO reviewDTO);
    public void updateReview(ReviewDTO reviewDTO, Review review);

    // 상품 리뷰 조회
    public List<ReviewDTO> showReview(Long productId);
}
