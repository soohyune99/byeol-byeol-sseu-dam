package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Course;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Mycourse;
import com.app.byeolbyeolsseudam.entity.Spot;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MycourseDTO {
    private Long mycourseId;
    private Member member;
    private Course course;
    private Spot spot;

    @QueryProjection
    public MycourseDTO(Long mycourseId, Member member, Course course, Spot spot) {
        this.mycourseId = mycourseId;
        this.member = member;
        this.course = course;
        this.spot = spot;
    }

    public Mycourse toEntity(){
        return Mycourse.builder()
                .member(member)
                .course(course)
                .spot(spot)
                .build();
    }
}
