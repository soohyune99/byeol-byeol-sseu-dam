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
    private Long memberId;
    private Long courseId;
    private Long spotId;

    @QueryProjection
    public MycourseDTO(Long mycourseId, Long memberId, Long courseId, Long spotId) {
        this.mycourseId = mycourseId;
        this.memberId = memberId;
        this.courseId = courseId;
        this.spotId = spotId;
    }

    public Mycourse toEntity(){
        return Mycourse.builder()
                .build();
    }
}
