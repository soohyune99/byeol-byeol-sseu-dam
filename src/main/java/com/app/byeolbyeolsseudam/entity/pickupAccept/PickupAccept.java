package com.app.byeolbyeolsseudam.entity.pickupAccept;

import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PICKUP_ACCEPT")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PickupAccept extends Period {
    @Id @GeneratedValue @NotNull
    private Long pickupAcceptId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Pickup pickup;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeMember(Member member){ this.member = member; }

    public void changePickup(Pickup pickup){
        this.pickup = pickup;
    }

}
