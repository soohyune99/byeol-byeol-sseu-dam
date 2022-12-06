package com.app.byeolbyeolsseudam.domain.mypoint;

import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MypointDTO {
    private Long mypointId;
    private String mypointContent;
    private int mypointInout;
    private LocalDateTime createdDate;
    private Long memberId;

    @QueryProjection
    public MypointDTO(Long mypointId, String mypointContent, int mypointInout, LocalDateTime createdDate, Long memberId) {
        this.mypointId = mypointId;
        this.mypointContent = mypointContent;
        this.mypointInout = mypointInout;
        this.createdDate = createdDate;
        this.memberId = memberId;
    }

    public Mypoint toEntity(){
        return Mypoint.builder()
                .mypointContent(mypointContent)
                .mypointInout(mypointInout)
                .build();
    }
}
