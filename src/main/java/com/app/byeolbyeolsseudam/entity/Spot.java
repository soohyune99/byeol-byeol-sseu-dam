package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SPOT")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends Period {
    @Id @GeneratedValue
    private Long spotId;
    private String spotName;
    private String spotFile;
    private String spotAddress;
    private int spotNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
}
