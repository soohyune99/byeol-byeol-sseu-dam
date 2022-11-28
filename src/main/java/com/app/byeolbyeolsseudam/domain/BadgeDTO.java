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
    private String badgeFileName;
    private String badgeFileUuid;
    private String badgeFilePath;

    @QueryProjection
    public BadgeDTO(Long badgeId, String badgeName, String badgeFileName, String badgeFileUuid, String badgeFilePath) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeFileName = badgeFileName;
        this.badgeFileUuid = badgeFileUuid;
        this.badgeFilePath = badgeFilePath;
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeName(badgeName)
                .badgeFileName(badgeFileName)
                .badgeFilePath(badgeFilePath)
                .badgeFileUuid(badgeFileUuid)
                .build();
    }
}