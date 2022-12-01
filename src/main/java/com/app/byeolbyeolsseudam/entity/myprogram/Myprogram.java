package com.app.byeolbyeolsseudam.entity.myprogram;

import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.program.Program;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
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

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeProgram(Program program){
        this.program = program;
    }

}
