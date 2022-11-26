package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Course;
import com.app.byeolbyeolsseudam.entity.CourseFinished;
import com.app.byeolbyeolsseudam.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class CourseFinishedDTO {
    private Long courseFinishedId;
    private String courseFinishedStatus;
    private Member member;
    private Course course;

    @QueryProjection
    public CourseFinishedDTO(Long courseFinishedId, String courseFinishedStatus, Member member, Course course) {
        this.courseFinishedId = courseFinishedId;
        this.courseFinishedStatus = courseFinishedStatus;
        this.member = member;
        this.course = course;
    }

    public CourseFinished toEntity(){
        return CourseFinished.builder()
                .courseFinishedStatus(courseFinishedStatus)
                .member(member)
                .course(course)
                .build();
    }
}
