package com.app.byeolbyeolsseudam.domain.pickup;

import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
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
    private Long memberId; // 신청자 아이디
    private String memberName; // 신청자 이름
    private Long pickerId; // 수거자 아이디
    private String pickerName; // 수거자 이름
    private LocalDateTime createdDate;

    @QueryProjection
    public PickupDTO(Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, LocalDateTime createdDate) {
        this.pickupId = pickupId;
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.memberId = memberId;
        this.memberName = memberName;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public PickupDTO(Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, LocalDateTime createdDate) {
        this.pickupId = pickupId;
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.memberId = memberId;
        this.createdDate = createdDate;
    }

    @QueryProjection
    public PickupDTO(Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, Long pickerId, String pickerName, LocalDateTime createdDate) {
        this.pickupId = pickupId;
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.memberId = memberId;
        this.memberName = memberName;
        this.pickerId = pickerId;
        this.pickerName = pickerName;
        this.createdDate = createdDate;
    }

    public Pickup toEntity(){
        Recyclable recyclable = new Recyclable();
        recyclable.setPetCount(petCount);
        recyclable.setGlassCount(glassCount);

        return Pickup.builder()
                .recyclable(recyclable)
                .pickupAddress(pickupAddress)
                .pickupMessage(pickupMessage)
                .pickupStatus(pickupStatus)
                .build();
    }
}
