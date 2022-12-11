package com.app.byeolbyeolsseudam.repository.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.QPickup;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.pickup.QPickup.pickup;

@Repository
@RequiredArgsConstructor
public class PickupCustomRepositoryImpl implements PickupCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PickupDTO> getMyPickupList(Long memberId, int page){
        return jpaQueryFactory.select(new QPickupDTO(pickup.pickupId,
                pickup.recyclable.petCount, pickup.recyclable.glassCount, pickup.pickupAddress,
                pickup.pickupMessage, pickup.pickupStatus, pickup.member.memberId,
                pickup.member.memberName, pickup.createdDate))
                .from(pickup)
                .where(pickup.member.memberId.eq(memberId))
                .orderBy(pickup.createdDate.desc())
                .offset(page * 3)
                .limit(3)
                .fetch();
    }

    @Override
    public PickupDTO getMyPickup(Long pickupId){
        return jpaQueryFactory.select(new QPickupDTO(pickup.pickupId,
                pickup.recyclable.petCount, pickup.recyclable.glassCount, pickup.pickupAddress,
                pickup.pickupMessage, pickup.pickupStatus, pickup.member.memberId,
                pickup.member.memberName, pickup.createdDate))
                .from(pickup)
                .where(pickup.pickupId.eq(pickupId))
                .fetchOne();
    }
}
