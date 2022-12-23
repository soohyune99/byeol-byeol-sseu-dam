package com.app.byeolbyeolsseudam.service.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.app.byeolbyeolsseudam.repository.review.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    // 리뷰 조회
    @Override
    public List<ReviewDTO> getReviewList(Long productId){
        return reviewRepository.getReviewList(productId);
    }

    // 리뷰 전체 조회
    @Override
    public List<ReviewDTO> getReviewAllList(Long productId){
        return reviewRepository.getAllReviewList(productId);
    }

    // 리뷰 더보기
    @Override
    public List<ReviewDTO> getMoreReview(Long productId, int page){
        return reviewRepository.getMoreReview(productId, page);
    }

    // 리뷰 저장
    @Override
    public void saveReview(ReviewDTO reviewDTO){
        Review review = reviewDTO.toEntity();
        review.changeMember(memberRepository.findById(reviewDTO.getMemberId()).get());
        review.changeProduct(productRepository.findById(reviewDTO.getProductId()).get());
        reviewRepository.save(review);
    }

    // 리뷰 파일만
    @Override
    public List<ReviewDTO> getReviewFileList(Long productId){
        return reviewRepository.getReviewFileList(productId);
    }



}
