package com.app.byeolbyeolsseudam.entity.mycourse;

import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.spot.Spot;
import com.app.byeolbyeolsseudam.entity.course.Course;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYCOURSE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mycourse extends Period {
    @Id @GeneratedValue @NotNull
    private Long mycourseId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeCourse(Course course){
        this.course = course;
    }

    public void changeSpot(Spot spot){
        this.spot = spot;
    }
}
