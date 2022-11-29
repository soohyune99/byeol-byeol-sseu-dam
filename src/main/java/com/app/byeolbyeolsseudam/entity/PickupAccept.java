package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
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
