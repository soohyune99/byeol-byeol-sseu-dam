package com.app.byeolbyeolsseudam.entity;

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
    private String courseFinishedStatus;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @Builder
    public CourseFinished(String courseFinishedStatus, Member member, Course course) {
        this.courseFinishedStatus = courseFinishedStatus;
        this.member = member;
        this.course = course;
    }

    public void update(String courseFinishedStatus, Course course){
        this.courseFinishedStatus = courseFinishedStatus;
        this.course = course;
    }
}
