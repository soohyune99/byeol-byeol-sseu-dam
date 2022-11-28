package com.app.byeolbyeolsseudam.entity;

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
public class Badge extends Period{
    @Id @GeneratedValue @NotNull
    private Long badgeId;
    @NotNull
    private String badgeName;
    @NotNull
    private String badgeFileName;
    @NotNull
    private String badgeFileUuid;
    @NotNull
    private String badgeFilePath;

    @Builder
    public Badge(String badgeName, String badgeFileName, String badgeFileUuid, String badgeFilePath) {
        this.badgeName = badgeName;
        this.badgeFileName = badgeFileName;
        this.badgeFilePath = badgeFilePath;
        this.badgeFileUuid = badgeFileUuid;
    }

    public void update(String badgeName, String badgeFileName, String badgeFilePath, String badgeFileUuid){
        this.badgeName = badgeName;
        this.badgeFileName = badgeFileName;
        this.badgeFilePath = badgeFilePath;
        this.badgeFileUuid = badgeFileUuid;

    }
}