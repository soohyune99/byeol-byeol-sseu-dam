package com.app.byeolbyeolsseudam.entity.mypoint;

import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYPOINT")
@Getter
@NoArgsConstructor/*(access = AccessLevel.PROTECTED)*/
public class Mypoint extends Period {
    @Id @GeneratedValue @NotNull
    private Long mypointId;
    @NotNull
    private String mypointContent;
    @NotNull
    private int mypointInout;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Mypoint(String mypointContent, int mypointInout) {
        this.mypointContent = mypointContent;
        this.mypointInout = mypointInout;
    }
}
