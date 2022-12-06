package com.app.byeolbyeolsseudam.controller.market;

import com.app.byeolbyeolsseudam.domain.comment.CommentDTO;
import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import com.app.byeolbyeolsseudam.entity.comment.Comment;
import com.app.byeolbyeolsseudam.entity.review.Review;
import com.app.byeolbyeolsseudam.service.market.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/review/*")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> saveReview(@RequestBody ReviewDTO reviewDTO) throws UnsupportedEncodingException {
        log.info("리뷰 내용 : " + reviewDTO.getReviewContent());
        log.info("회원 이름 : " + reviewDTO.getMemberName());
        log.info("상품 이름 : " + reviewDTO.getProductId());
        Review review = reviewService.saveReview(reviewDTO);
        reviewService.updateReview(reviewDTO, review);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }


    //    특정 게시글의 댓글 전체 조회
    @GetMapping("/list/{pno}")
    public List<ReviewDTO> list(@PathVariable("pno") Long productId){
        return reviewService.showReview(productId);
    }


}
