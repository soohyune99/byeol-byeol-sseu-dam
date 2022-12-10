package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ReviewDTO> getReviewList(Long productId){
        return reviewRepository.getReviewList(productId);
    }

    @Override
    public List<ReviewDTO> getMoreReview(Long productId, int page){
        return reviewRepository.getMoreReview(productId, page);
    }

    @Override
    public void saveReview(ReviewDTO reviewDTO){
        Review review = reviewDTO.toEntity();
        review.changeMember(memberRepository.findById(reviewDTO.getMemberId()).get());
        review.changeProduct(productRepository.findById(reviewDTO.getProductId()).get());
        reviewRepository.save(review);
    }



}
