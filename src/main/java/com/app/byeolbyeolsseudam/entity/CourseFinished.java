package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_COURSE_FINISHED")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseFinished extends Period {
    @Id @GeneratedValue
    private Long courseFinishedId;
    private String courseFinishedStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

}
