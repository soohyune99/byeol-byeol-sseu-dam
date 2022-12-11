package com.app.byeolbyeolsseudam.repository.review;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;

import java.util.List;

public interface ReviewCustomRepository {

    /* 리뷰 조회 */
    public List<ReviewDTO> getReviewList(Long productId);

    /* 리뷰 전체 조회 */
    public List<ReviewDTO> getAllReviewList(Long productId);

    /* 리뷰 더보기 */
    public List<ReviewDTO> getMoreReview(Long productId, int page);

    /* 사진 있는 리뷰만 조회 */
    public List<ReviewDTO> getReviewFileList(Long productId);
}
