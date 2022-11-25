package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_PICKUP")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Pickup extends Period {
    @Id @GeneratedValue
    private Long pickupId;
    @Embedded
    private Recyclable recyclable;
    private String pickupAddress;
    private String pickupMessage;
    @Enumerated(EnumType.STRING)
    private PickupStatus pickupStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
