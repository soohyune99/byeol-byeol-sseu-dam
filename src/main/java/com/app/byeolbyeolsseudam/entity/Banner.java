package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BANNER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period{
    @Id @GeneratedValue @NotNull
    private Long bannerId;
    @NotNull
    private String bannerName;
    @NotNull
    private String bannerPath;
    @NotNull
    private String bannerUuid;

    @Builder
    public Banner(String bannerName, String bannerPath, String bannerUuid) {
        this.bannerName = bannerName;
        this.bannerPath = bannerPath;
        this.bannerUuid = bannerUuid;
    }

    public void update(String bannerName, String bannerPath, String bannerUuid) {
        this.bannerName = bannerName;
        this.bannerPath = bannerPath;
        this.bannerUuid = bannerUuid;
    }
}
