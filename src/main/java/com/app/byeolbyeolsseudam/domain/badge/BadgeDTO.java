package com.app.byeolbyeolsseudam.domain.badge;

import com.app.byeolbyeolsseudam.entity.badge.Badge;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class BadgeDTO {
    private Long badgeId;
    private String badgeName;
    private String badgeInfo;
    private String badgeFileName;
    private String badgeFileUuid;
    private String badgeFilePath;

    @QueryProjection
    public BadgeDTO(Long badgeId, String badgeName, String badgeInfo, String badgeFileName) {
        this.badgeId = badgeId;
        this.badgeName = badgeName;
        this.badgeInfo = badgeInfo;
        this.badgeFileName = badgeFileName;
    }

    public Badge toEntity(){
        return Badge.builder()
                .badgeName(badgeName)
                .badgeInfo(badgeInfo)
                .badgeFileName(badgeFileName)
                .build();
    }
}