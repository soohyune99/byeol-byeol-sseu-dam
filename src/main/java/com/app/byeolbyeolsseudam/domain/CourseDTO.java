package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.entity.Course;
import com.app.byeolbyeolsseudam.entity.Spot;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class CourseDTO {
    private Long courseId;
    private String courseName;
    private String courseArea;
    private String courseDistance;
    private String courseTime;
    private String courseStart;
    private String courseFinish;
    private CourseGrade courseGrade;
    private String courseFileName;
    private String courseFilePath;
    private String courseFileUuid;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;

    private List<Spot> spots;

    @QueryProjection
    public CourseDTO(Long courseId, String courseName, String courseArea, String courseDistance, String courseTime, String courseStart, String courseFinish, CourseGrade courseGrade, String courseFileName, String courseFilePath, String courseFileUuid, LocalDateTime openingDate, LocalDateTime closingDate, List<Spot> spots) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseArea = courseArea;
        this.courseDistance = courseDistance;
        this.courseTime = courseTime;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.courseGrade = courseGrade;
        this.courseFileName = courseFileName;
        this.courseFilePath = courseFilePath;
        this.courseFileUuid = courseFileUuid;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.spots = spots;
    }

    public Course toEntity(List<Spot> spots){
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setOpeningDate(openingDate);
        possibleDate.setClosingDate(closingDate);

        return Course.builder()
                .courseName(courseName)
                .courseArea(courseArea)
                .courseFileName(courseFileName)
                .courseFilePath(courseFilePath)
                .courseFileUuid(courseFileUuid)
                .courseDistance(courseDistance)
                .courseTime(courseTime)
                .courseGrade(courseGrade)
                .courseStart(courseStart)
                .courseFinish(courseFinish)
                .possibleDate(possibleDate)
                .spots(spots)
                .build();
    }
}
