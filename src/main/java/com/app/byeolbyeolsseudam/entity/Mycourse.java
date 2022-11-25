package com.app.byeolbyeolsseudam.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYCOURSE")
public class Mycourse extends Period {
    @Id @GeneratedValue
    private Long mycourseId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;
    @ManyToOne(fetch = FetchType.LAZY)
    private Spot spot;
}
