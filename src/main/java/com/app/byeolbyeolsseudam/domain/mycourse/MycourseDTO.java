package com.app.byeolbyeolsseudam.domain.mycourse;

import com.app.byeolbyeolsseudam.entity.mycourse.Mycourse;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MycourseDTO {
    private Long mycourseId;
    private CourseFinishedStatus courseFinishedStatus;
    private Long memberId;
    private Long courseId;
    private String courseName;
    private Long spotId;
    private String spotName;
    private int spotNumber;
    private LocalDateTime createdDate;

    @QueryProjection
    public MycourseDTO(Long mycourseId, CourseFinishedStatus courseFinishedStatus, Long memberId, Long courseId, String courseName, Long spotId, String spotName, int spotNumber, LocalDateTime createdDate) {
        this.mycourseId = mycourseId;
        this.courseFinishedStatus = courseFinishedStatus;
        this.memberId = memberId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.spotId = spotId;
        this.spotName = spotName;
        this.spotNumber = spotNumber;
        this.createdDate = createdDate;
    }

    public Mycourse toEntity(){
        return Mycourse.builder()
                .courseFinishedStatus(courseFinishedStatus)
                .build();
    }
}
