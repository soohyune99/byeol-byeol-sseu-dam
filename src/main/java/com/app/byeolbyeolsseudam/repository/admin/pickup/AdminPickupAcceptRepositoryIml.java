package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.QPickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept.pickupAccept;

@Repository
@RequiredArgsConstructor
public class AdminPickupAcceptRepositoryIml implements AdminPickupAcceptCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

//    @Override
//    public PickupAcceptDTO selectByPickId(Long pickupId) {
//        return jpaQueryFactory.select(new QPickupAcceptDTO(
//                pickupAccept.pickupAcceptId,
//                pickupAccept.pickup.pickupId,
//                pickupAccept.pickup.recyclable.petCount,
//                pickupAccept.pickup.recyclable.glassCount,
//                pickupAccept.pickup.pickupAddress,
//                pickupAccept.pickup.pickupMessage,
//                pickupAccept.pickup.pickupStatus,
//                pickupAccept.member.memberId,
//                pickupAccept.member.memberName,
//                pickupAccept.createdDate
//        )).from(pickupAccept)
//                .where(pickupAccept.pickup.pickupId.eq(pickupId))
//                .fetchOne();
//    }

}
