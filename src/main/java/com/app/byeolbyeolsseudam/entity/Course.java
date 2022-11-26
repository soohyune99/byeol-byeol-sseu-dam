package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private String courseFile;
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
    @Embedded
    private PossibleDate possibleDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Spot> spots;

    @Builder
    public Course(String courseName, String courseArea, String courseFile, String courseDistance, String courseTime, CourseGrade courseGrade, String courseStart, String courseFinish, LocalDateTime openingDate, LocalDateTime closingDate) {
        this.courseName = courseName;
        this.courseArea = courseArea;
        this.courseFile = courseFile;
        this.courseDistance = courseDistance;
        this.courseTime = courseTime;
        this.courseGrade = courseGrade;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.possibleDate.setOpeningDate(openingDate);
        this.possibleDate.setClosingDate(closingDate);
    }

    public void update(String courseName, String courseArea, String courseFile, String courseDistance, String courseTime, CourseGrade courseGrade, String courseStart, String courseFinish, LocalDateTime openingDate, LocalDateTime closingDate){
        this.courseName = courseName;
        this.courseArea = courseArea;
        this.courseFile = courseFile;
        this.courseDistance = courseDistance;
        this.courseTime = courseTime;
        this.courseGrade = courseGrade;
        this.courseStart = courseStart;
        this.courseFinish = courseFinish;
        this.possibleDate.setOpeningDate(openingDate);
        this.possibleDate.setClosingDate(closingDate);
    }
}
