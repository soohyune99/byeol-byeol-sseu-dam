package com.app.byeolbyeolsseudam.entity.mybadge;

import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.badge.Badge;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MYBADGE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mybadge extends Period {
    @Id @GeneratedValue @NotNull
    private Long mybadgeId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Badge badge;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeBadge(Badge badge){
        this.badge = badge;
    }

}
