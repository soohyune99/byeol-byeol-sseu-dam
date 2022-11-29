package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PICKUP")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pickup extends Period {
    @Id @GeneratedValue @NotNull
    private Long pickupId;
    @Embedded @NotNull
    private Recyclable recyclable;
    @NotNull
    private String pickupAddress;
    private String pickupMessage;
    @NotNull
    @Enumerated(EnumType.STRING)
    private PickupStatus pickupStatus;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Pickup(Recyclable recyclable, String pickupAddress, String pickupMessage, PickupStatus pickupStatus) {
        this.recyclable = recyclable;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
    }

    public void update(PickupStatus pickupStatus) {
        this.pickupStatus = pickupStatus;
    }
}
