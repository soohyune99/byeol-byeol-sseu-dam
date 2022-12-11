package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {

    public List<ReviewDTO> getReviewList(Long productId);

    public List<ReviewDTO> getReviewAllList(Long productId);

    public List<ReviewDTO> getMoreReview(Long productId, int page);

    public List<ReviewDTO> getReviewFileList(Long productId);

    public void saveReview(ReviewDTO reviewDTO);

}
