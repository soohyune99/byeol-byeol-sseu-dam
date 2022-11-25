package com.app.byeolbyeolsseudam.entity;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PICKUP_ACCEPT")
public class PickupAccept extends Period {
    @Id @GeneratedValue
    private Long pickupAcceptId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pickup pickup;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
