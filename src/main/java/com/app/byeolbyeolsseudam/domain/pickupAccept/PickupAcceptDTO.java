package com.app.byeolbyeolsseudam.domain.pickupAccept;

import com.app.byeolbyeolsseudam.entity.member.Member;
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
public class PickupAcceptDTO {
    private Long pickupAcceptId;
    private Long pickupId;
    private int petCount;
    private int glassCount;
    private String pickupAddress;
    private String pickupMessage;
    private PickupStatus pickupStatus;
    private Long memberId;
    private String memberName;
    private LocalDateTime createdDate;
    private Member member;
    private Pickup pickup;

    @QueryProjection
    public PickupAcceptDTO(Long pickupAcceptId, Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, LocalDateTime createdDate) {
        this.pickupAcceptId = pickupAcceptId;
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

}
