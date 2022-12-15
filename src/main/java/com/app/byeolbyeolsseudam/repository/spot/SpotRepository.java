package com.app.byeolbyeolsseudam.repository.spot;

import com.app.byeolbyeolsseudam.entity.spot.Spot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepository extends JpaRepository<Spot, Long> {
    public Spot findBySpotNumberAndCourseCourseName(int spotNumber, String courseName);
}
