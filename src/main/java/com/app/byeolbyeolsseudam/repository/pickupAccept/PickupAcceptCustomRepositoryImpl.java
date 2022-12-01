package com.app.byeolbyeolsseudam.repository.pickupAccept;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.QPickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PickupAcceptCustomRepositoryImpl implements PickupAcceptCustomRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<PickupDTO> findAllByPickupStatus() {

        return queryFactory.select(new QPickupDTO(
                QPickup.pickup.pickupId,
                QPickup.pickup.recyclable.petCount,
                QPickup.pickup.recyclable.glassCount,
                QPickup.pickup.pickupAddress,
                QPickup.pickup.member.memberName,
                QPickup.pickup.pickupStatus,
                QPickup.pickup.member.memberId,
                QPickup.pickup.createdDate
        )).from(QPickup.pickup)
                .where(QPickup.pickup.pickupStatus.eq(PickupStatus.수거대기중))
                .fetch();

    }

    @Override
    public List<PickupDTO> findAllByMyPickup(Long memberId) {

        return queryFactory.select(new QPickupDTO(
                QPickup.pickup.pickupId,
                QPickup.pickup.recyclable.petCount,
                QPickup.pickup.recyclable.glassCount,
                QPickup.pickup.pickupAddress,
                QPickup.pickup.member.memberName,
                QPickup.pickup.pickupStatus,
                QPickup.pickup.member.memberId,
                QPickup.pickup.createdDate
        ))
                .from(QPickup.pickup, QPickupAccept.pickupAccept)
                .where(
                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거중),
                        QPickupAccept.pickupAccept.member.memberId.eq(memberId)
                )
                .distinct()
                .fetch();

//.join(QPickupAccept.pickupAccept)
//                .on(QPickup.pickup.pickupStatus.eq(PickupStatus.수거중))
//        QPickup.pickup.pickupStatus.eq(PickupStatus.수거중),
    }


    @Override
    public List<PickupDTO> findAllByComplete(Long memberId) {

        return queryFactory.select(new QPickupDTO(
                QPickup.pickup.pickupId,
                QPickup.pickup.recyclable.petCount,
                QPickup.pickup.recyclable.glassCount,
                QPickup.pickup.pickupAddress,
                QPickup.pickup.member.memberName,
                QPickup.pickup.pickupStatus,
                QPickup.pickup.member.memberId,
                QPickup.pickup.createdDate
        ))
                .from(QPickup.pickup, QPickupAccept.pickupAccept)
                .where(
                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거완료),
                        QPickupAccept.pickupAccept.member.memberId.eq(memberId)
                )
                .distinct()
                .fetch();


//    @Override
//    public Page<PickupDTO> findByPickupName(Pageable pageable){
//
//        List<PickupDTO> pickups = queryFactory.selectFrom(QPickup.pickup)
//                .where(QPickup.pickup.member.memberName.eq("장선홍"))
//                .orderBy(QPickup.pickup.pickupId.desc())
//                .offset(pageable.getOffset())
//                .limit(pageable.getPageSize())
//                .fetch();
//
//        int total = queryFactory.selectFrom(QPickup.pickup)
//                .where(QPickup.pickup.member.memberName.eq("장선홍"))
//                .fetch().size();
//
//        return new PageImpl(pickups, pageable, total);
//
//    }


    }

}
