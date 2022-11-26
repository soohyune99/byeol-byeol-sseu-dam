package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Course;
import com.app.byeolbyeolsseudam.entity.Spot;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SpotDTO {
    private Long spotId;
    private String spotName;
    private String spotFile;
    private String spotAddress;
    private int spotNumber;
    private Course course;

    @QueryProjection
    public SpotDTO(Long spotId, String spotName, String spotFile, String spotAddress, int spotNumber, Course course) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotFile = spotFile;
        this.spotAddress = spotAddress;
        this.spotNumber = spotNumber;
        this.course = course;
    }

    public Spot toEntity(){
        return Spot.builder()
                .spotName(spotName)
                .spotFile(spotFile)
                .spotAddress(spotAddress)
                .spotNumber(spotNumber)
                .course(course)
                .build();
    }
}
