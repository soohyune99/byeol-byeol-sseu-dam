package com.app.byeolbyeolsseudam.repository.review;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;

import java.util.List;

public interface ReviewCustomRepository {

    // 리뷰 등록
    public void saveReview(ReviewDTO reviewDTO, Review review);

    // 상품 리뷰 조회
    public List<ReviewDTO> showAll(Long productId);
}
