package com.app.byeolbyeolsseudam.repository.admin.review;

import com.app.byeolbyeolsseudam.domain.review.ReviewDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminReviewCustomRepository {
    public List<ReviewDTO> showReviewList(Pageable pageable);
}
