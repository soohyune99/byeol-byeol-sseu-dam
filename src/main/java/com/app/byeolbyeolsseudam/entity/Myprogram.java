package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYPROGRAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Myprogram extends Period {
    @Id @GeneratedValue @NotNull
    private Long myprogramId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Program program;

    public void changeProgram(Program program){
        this.program = program;
    }

    @Builder
    public Myprogram() {;}

    public void update(Program program){
        this.program = program;
    }
}
