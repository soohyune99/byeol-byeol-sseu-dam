package com.app.byeolbyeolsseudam.domain.mycourse;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

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

}
