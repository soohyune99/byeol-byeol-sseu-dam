package com.app.byeolbyeolsseudam.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYPROGRAM")
public class Myprogram extends Period {
    @Id @GeneratedValue
    private Long myprogramId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Program program;
}
