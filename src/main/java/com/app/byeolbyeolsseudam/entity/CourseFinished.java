package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COURSE_FINISHED")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseFinished extends Period {
    @Id @GeneratedValue @NotNull
    private Long courseFinishedId;
    @NotNull
    private CourseFinishedStatus courseFinishedStatus;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public void changeCourse(Course course){
        this.course = course;
    }

    @Builder
    public CourseFinished(CourseFinishedStatus courseFinishedStatus) {
        this.courseFinishedStatus = courseFinishedStatus;
    }

    public void update(CourseFinishedStatus courseFinishedStatus, Course course){
        this.courseFinishedStatus = courseFinishedStatus;
        this.course = course;
    }
}
