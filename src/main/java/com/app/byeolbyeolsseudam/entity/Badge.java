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
    private String badgeFile;

    @Builder
    public Badge(String badgeName, String badgeFile) {
        this.badgeName = badgeName;
        this.badgeFile = badgeFile;
    }

    public void update(String badgeName, String badgeFile){
        this.badgeName = badgeName;
        this.badgeFile = badgeFile;
    }
}