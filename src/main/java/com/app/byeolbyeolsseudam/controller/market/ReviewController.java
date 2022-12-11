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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = {"/review/*", "/review"})
public class ReviewController {
    private final ReviewService reviewService;

    /* 리뷰 조회 */
    @GetMapping("/{productId}")
    public List<ReviewDTO> getReviewList(@PathVariable Long productId){
        return reviewService.getReviewList(productId);
    }

    /* 리뷰 전체 조회 */
    @PostMapping("/all/{productId}")
    public List<ReviewDTO> getReviewAllList(@PathVariable Long productId){
        return reviewService.getReviewAllList(productId);
    }

    /* 사진 리뷰만 보기*/
    @PostMapping("/photo/{productId}")
    public List<ReviewDTO> getReviewFile(@PathVariable Long productId){
        return reviewService.getReviewFileList(productId);
    }

    /* 리뷰 더보기 */
    @GetMapping("/{productId}/{page}")
    public List<ReviewDTO> getMoreReview(@PathVariable Long productId, @PathVariable int page){
        return reviewService.getMoreReview(productId, page);
    }

    /* 리뷰 작성 */
    @PostMapping(value = "/new", consumes = "application/json", produces = "text/plain; charset=utf-8")
    public ResponseEntity<String> saveComment(@RequestBody ReviewDTO reviewDTO) throws UnsupportedEncodingException {
        log.info("---------------여기 리뷰 컨트롤러-----------------------" );
        reviewService.saveReview(reviewDTO);
        return new ResponseEntity<>(new String("write success".getBytes(), "UTF-8"), HttpStatus.OK);
    }

    /* 리뷰 사진 첨부*/
    @ResponseBody
    @PostMapping("/upload")
    public void uploadReviewFile(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception{
        response.setContentType("text/html; charset=utf-8");
        String uploadPath = "C:/upload/review/";

        File uploadFolder = new File(uploadPath, createDirectoryByNow());
        if(!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }

        PrintWriter out = response.getWriter();
        String originalFileExtension = file.getOriginalFilename();
        String storedFileName = UUID.randomUUID().toString().replaceAll("-", "");// + originalFileExtension
        log.info("storedFileName : " + storedFileName);
        log.info("originalFileExtension : " + originalFileExtension);
        log.info(file.toString());
        log.info(uploadPath + storedFileName);
        file.transferTo(new File(uploadFolder + "/" + storedFileName + originalFileExtension));
        out.print("/upload/review/" + createDirectoryByNow() + "/" + storedFileName + originalFileExtension);
        out.close();
    }

    public String createDirectoryByNow(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        return format.format(now);
    }



}
