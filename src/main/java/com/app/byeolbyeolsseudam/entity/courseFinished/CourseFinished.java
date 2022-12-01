package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.CourseFinishedDTO;
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
    private Mycourse mycourse;

    public void changeMycourse(Mycourse mycourse){
        this.mycourse = mycourse;
    }

    @Builder
    public CourseFinished(CourseFinishedStatus courseFinishedStatus) {
        this.courseFinishedStatus = courseFinishedStatus;
    }

    public void update(CourseFinishedDTO couseFinishedDTO){
        this.courseFinishedStatus = couseFinishedDTO.getCourseFinishedStatus();
    }
}
