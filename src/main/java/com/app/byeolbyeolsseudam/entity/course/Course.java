package com.app.byeolbyeolsseudam.entity.course;

import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_COURSE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends Period {
    @Id @GeneratedValue @NotNull
    private Long courseId;
    @NotNull
    private String courseName;
    @NotNull
    private String courseArea;
    @NotNull
    private String courseDistance;
    @NotNull
    private String courseTime;
    @NotNull
    @Enumerated(EnumType.STRING)
    private CourseGrade courseGrade;
    @NotNull
    private String courseStart;
    @NotNull
    private String courseFinish;
    @NotNull
    private String courseFileName;
    @NotNull
    private String courseFilePath;
    @NotNull
    private String courseFileUuid;
    @Embedded
    private PossibleDate possibleDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Spot> spots;

    @Builder
    public Course(String courseName, String courseArea, String courseDistance, String courseTime, CourseGrade courseGrade, String courseStart, String courseFinish, String courseFileName, String courseFilePath, String courseFileUuid, PossibleDate possibleDate) {
        this.courseName = courseName;
        this.courseArea = courseArea;
        this.courseDistance = courseDistance;
        this.courseTime = courseTime;
        this.courseGrade = courseGrade;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.courseFileName = courseFileName;
        this.courseFilePath = courseFilePath;
        this.courseFileUuid = courseFileUuid;
        this.possibleDate = possibleDate;
    }

    public void update(CourseDTO courseDTO) {
        this.courseName = courseDTO.getCourseName();
        this.courseArea = courseDTO.getCourseArea();
        this.courseDistance = courseDTO.getCourseDistance();
        this.courseTime = courseDTO.getCourseTime();
        this.courseGrade = courseDTO.getCourseGrade();
        this.courseStart = courseDTO.getCourseStart();
        this.courseFinish = courseDTO.getCourseFinish();
        this.courseFileName = courseDTO.getCourseFileName();
        this.courseFilePath = courseDTO.getCourseFilePath();
        this.courseFileUuid = courseDTO.getCourseFileUuid();
        this.possibleDate.setOpeningDate(courseDTO.getOpeningDate());
        this.possibleDate.setClosingDate(courseDTO.getClosingDate());
    }
}
