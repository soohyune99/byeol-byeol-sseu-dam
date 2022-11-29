package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Course;
import com.app.byeolbyeolsseudam.entity.CourseFinished;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.type.CourseFinishedStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class CourseFinishedDTO {
    private Long courseFinishedId;
    private CourseFinishedStatus courseFinishedStatus;
    private Long memberId;
    private Long courseId;

    @QueryProjection
    public CourseFinishedDTO(Long courseFinishedId, CourseFinishedStatus courseFinishedStatus, Long memberId, Long courseId) {
        this.courseFinishedId = courseFinishedId;
        this.courseFinishedStatus = courseFinishedStatus;
        this.memberId = memberId;
        this.courseId = courseId;
    }

    public CourseFinished toEntity(){
        return CourseFinished.builder()
                    .courseFinishedStatus(courseFinishedStatus)
                    .build();
    }
}
