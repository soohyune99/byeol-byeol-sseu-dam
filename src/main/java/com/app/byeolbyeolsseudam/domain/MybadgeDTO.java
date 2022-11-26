package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Mybadge;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MybadgeDTO {
    private Long mybadgeId;
    private Member member;
    private Badge badge;
    private LocalDateTime createdDate;

    @QueryProjection
    public MybadgeDTO(Long mybadgeId, Member member, Badge badge, LocalDateTime createdDate) {
        this.mybadgeId = mybadgeId;
        this.member = member;
        this.badge = badge;
        this.createdDate = createdDate;
    }

    public Mybadge toEntity(){
        return Mybadge.builder()
                .member(member)
                .badge(badge)
                .build();
    }
}
