package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_BADGE")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Badge extends Period{
    @Id @GeneratedValue
    private Long badgeId;
    private String badgeName;
    private String badgeFile;
}
