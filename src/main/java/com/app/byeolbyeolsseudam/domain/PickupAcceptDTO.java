package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Pickup;
import com.app.byeolbyeolsseudam.entity.PickupAccept;
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
    private Pickup pickup;
    private Member member;

    @QueryProjection
    public PickupAcceptDTO(Long pickupAcceptId, Pickup pickup, Member member) {
        this.pickupAcceptId = pickupAcceptId;
        this.pickup = pickup;
        this.member = member;
    }

    public PickupAccept toEntity(){
        return PickupAccept.builder()
                .pickup(pickup)
                .member(member)
                .build();
    }
}
