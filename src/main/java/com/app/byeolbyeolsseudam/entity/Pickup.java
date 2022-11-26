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

    @Builder
    public Pickup(int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Member member) {
        this.recyclable.setPetCount(petCount);
        this.recyclable.setGlassCount(glassCount);
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.member = member;
    }

    public void update(Recyclable recyclable, String pickupAddress, String pickupMessage, PickupStatus pickupStatus){
        this.recyclable = recyclable;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;

    }
}
