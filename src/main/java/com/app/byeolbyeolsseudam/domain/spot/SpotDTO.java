package com.app.byeolbyeolsseudam.domain.spot;

import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
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
    private String spotAddress;
    private int spotNumber;
    private String spotQrName;
    private String spotQrPath;
    private String spotQrUuid;
    private Long courseId;



    @QueryProjection
    public SpotDTO(Long spotId, String spotName, String spotAddress, int spotNumber, Long courseId) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotAddress = spotAddress;
        this.spotNumber = spotNumber;
        this.courseId = courseId;
    }

    @QueryProjection
    public SpotDTO(Long spotId, String spotName, String spotAddress, int spotNumber, String spotQrName, String spotQrPath, String spotQrUuid) {
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotAddress = spotAddress;
        this.spotNumber = spotNumber;
        this.spotQrName = spotQrName;
        this.spotQrPath = spotQrPath;
        this.spotQrUuid = spotQrUuid;
    }


    public Spot toEntity(){
        return Spot.builder()
                .spotName(spotName)
                .spotQrName(spotQrName)
                .spotQrPath(spotQrPath)
                .spotQrUuid(spotQrUuid)
                .spotAddress(spotAddress)
                .spotNumber(spotNumber)
                .build();
    }
}
