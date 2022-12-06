package com.app.byeolbyeolsseudam.entity.badge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.entity.Period;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BADGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge extends Period {
    @Id @GeneratedValue @NotNull
    private Long badgeId;
    @NotNull
    private String badgeName;
    @NotNull
    private String badgeInfo;
    @NotNull
    private String badgeFileName;
    @NotNull
    private String badgeFileUuid;
    @NotNull
    private String badgeFilePath;

    @Builder
    public Badge(String badgeName, String badgeInfo, String badgeFileName, String badgeFileUuid, String badgeFilePath) {
        this.badgeName = badgeName;
        this.badgeInfo = badgeInfo;
        this.badgeFileName = badgeFileName;
        this.badgeFilePath = badgeFilePath;
        this.badgeFileUuid = badgeFileUuid;
    }

    public void update(BadgeDTO badgeDTO){
        this.badgeName = badgeDTO.getBadgeName();
        this.badgeInfo = badgeDTO.getBadgeInfo();
        this.badgeFileName = badgeDTO.getBadgeFileName();
        this.badgeFilePath = badgeDTO.getBadgeFilePath();
        this.badgeFileUuid = badgeDTO.getBadgeFileUuid();
    }
}