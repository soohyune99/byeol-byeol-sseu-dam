package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.QPickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickup.QPickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.pickup.QPickup.pickup;
import static com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept.pickupAccept;

@Repository
@RequiredArgsConstructor
public class AdminPickupRepositoryImpl implements AdminPickupCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<PickupDTO> showPickupList(Pageable pageable) {
        return jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
                pickupAccept.member.memberId,
                pickupAccept.member.memberName,
                pickup.createdDate
                )).from(pickup)
                .join(pickupAccept)
                .on(pickup.pickupId.eq(pickupAccept.pickup.pickupId))
                .orderBy(pickup.pickupId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public PickupDTO selectById(Long pickupId) {
        return jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
                pickupAccept.member.memberId,
                pickupAccept.member.memberName,
                pickup.createdDate
        )).from(pickup)
                .join(pickupAccept)
                .on(pickup.pickupId.eq(pickupAccept.pickup.pickupId))
                .where(pickup.pickupId.eq(pickupId))
                .orderBy(pickup.pickupId.desc())
                .fetchOne();
    }


}
