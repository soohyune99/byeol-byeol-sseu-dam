package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BANNER")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Banner extends Period{
    @Id @GeneratedValue
    private Long bannerId;
    private String bannerName;
}
