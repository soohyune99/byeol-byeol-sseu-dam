package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.type.CourseGrade;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "TBL_COURSE")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course extends Period {
    @Id @GeneratedValue
    private Long courseId;
    private String courseName;
    private String courseArea;
    private String courseFile;
    private String courseDistance;
    private String courseTime;
    @Enumerated(EnumType.STRING)
    private CourseGrade courseGrade;
    private String courseStart;
    private String courseFinish;
    @Embedded
    private PossibleDate possibleDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
    private List<Spot> spots;
}
