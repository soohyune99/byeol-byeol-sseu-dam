package com.app.byeolbyeolsseudam.repository.admin.review;

import com.app.byeolbyeolsseudam.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminReviewRepository extends JpaRepository<Review, Long>, AdminReviewCustomRepository {




}
