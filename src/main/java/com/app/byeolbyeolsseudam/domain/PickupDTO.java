package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Pickup;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class PickupDTO {
    private Long pickupId;
    private int petCount;
    private int glassCount;
    private String pickupAddress;
    private String pickupMessage;
    private PickupStatus pickupStatus;
    private Member member;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public PickupDTO(int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Member member) {
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.member = member;
    }

    public Pickup toEntity(){
        Recyclable recyclable = new Recyclable();
        recyclable.setPetCount(petCount);
        recyclable.setGlassCount(glassCount);

        return Pickup.builder()
                .recyclable(recyclable)
                .pickupAddress(pickupAddress)
                .pickupMessage(pickupMessage)
                .pickupStatus(PickupStatus.수거대기중)
                .member(member)
                .build();
    }
}
