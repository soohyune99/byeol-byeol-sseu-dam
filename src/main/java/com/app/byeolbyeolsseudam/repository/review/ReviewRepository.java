package com.app.byeolbyeolsseudam.repository.review;

import com.app.byeolbyeolsseudam.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewCustomRepository {
}
