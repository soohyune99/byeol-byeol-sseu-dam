package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BadgeDTO {
    private Long badgeId;
    private String badgeName;
    private String badgeFile;

    @QueryProjection
    public BadgeDTO(Long badgeId, String badgeName, String badgeFile) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeFile = badgeFile;
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeName(badgeName)
                .badgeFile(badgeFile)
                .build();
    }
}