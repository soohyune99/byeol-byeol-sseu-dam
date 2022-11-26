package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_SPOT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot extends Period {
    @Id @GeneratedValue @NotNull
    private Long spotId;
    @NotNull
    private String spotName;
    @NotNull
    private String spotFile;
    @NotNull
    private String spotAddress;
    @NotNull
    private int spotNumber;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    public void changeCourse(Course course){
        this.course = course;
        course.getSpots().add(this);
    }

    @Builder
    public Spot(String spotName, String spotFile, String spotAddress, int spotNumber, Course course) {
        this.spotName = spotName;
        this.spotFile = spotFile;
        this.spotAddress = spotAddress;
        this.spotNumber = spotNumber;
        this.course = course;
    }

    public void update(String spotName, String spotFile, String spotAddress, int spotNumber, Course course){
        this.spotName = spotName;
        this.spotFile = spotFile;
        this.spotAddress = spotAddress;
        this.spotNumber = spotNumber;
        this.course = course;
    }
}
