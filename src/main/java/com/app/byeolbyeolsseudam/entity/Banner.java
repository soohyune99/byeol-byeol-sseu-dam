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

    @Builder
    public Banner(String bannerName) {
        this.bannerName = bannerName;
    }

    public void update(String bannerName){
        this.bannerName = bannerName;
    }
}
