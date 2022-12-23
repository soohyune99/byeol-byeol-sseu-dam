package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    // 리뷰 조회
    public List<ReviewDTO> getReviewList(Long productId);

    // 리뷰 전체 조회
    public List<ReviewDTO> getReviewAllList(Long productId);

    // 리뷰 더보기
    public List<ReviewDTO> getMoreReview(Long productId, int page);

    // 리뷰 저장
    public void saveReview(ReviewDTO reviewDTO);

    // 리뷰 파일만
    public List<ReviewDTO> getReviewFileList(Long productId);

}
