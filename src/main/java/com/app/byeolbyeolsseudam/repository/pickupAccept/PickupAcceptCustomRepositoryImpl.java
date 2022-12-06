package com.app.byeolbyeolsseudam.repository.pickupAccept;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.entity.pickup.QPickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept.*;

@Repository
@RequiredArgsConstructor
public class PickupAcceptCustomRepositoryImpl implements PickupAcceptCustomRepository {
    private final JPAQueryFactory queryFactory;
    private final JPAQueryFactory jpaQueryFactory;
    private final PickupRepository pickupRepository;



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
                .orderBy(QPickup.pickup.createdDate.desc())
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
                .from(QPickup.pickup, pickupAccept)
                .where(
                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거중),
                        pickupAccept.member.memberId.eq(memberId)
                )
                .orderBy(QPickup.pickup.createdDate.desc())
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
                .from(QPickup.pickup, pickupAccept)
                .where(
                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거완료),
                        pickupAccept.member.memberId.eq(memberId)
                )
                .orderBy(QPickup.pickup.createdDate.desc())
                .distinct()
                .fetch();
    }

    @Override
    public PickupDTO find(Long pickupId){

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
               .from(QPickup.pickup, pickupAccept)
               .where(
                       QPickup.pickup.pickupId.eq(pickupId)
               ).fetchOne();
    }


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


//        픽업 수락하기 (PickUp Accept 저장하기)
//        @Override
//        public void Accept(Long memberId){
//            pickupRepository.findById(1L).get().getMember().getMemberId();
//
//            jpaQueryFactory.insert(pickupAccept)
//                    .columns(pickupAccept.member.memberId, pickupAccept.pickup.pickupId)
//                    .values(pickupRepository.findById(1L).get().getMember().getMemberId(),
//                            memberId)
//                    .
//
//
//        }

//        상태 진행중으로 업데이트
        @Override
        public void StatusUpdate(Long pickupId){
            jpaQueryFactory.update(QPickup.pickup).set(QPickup.pickup.pickupStatus, PickupStatus.수거중)
                    .where(QPickup.pickup.pickupId.eq(pickupId)).execute();

        }


//        완료 업데이트
        @Override
        public void Complete(Long pickupId){
            jpaQueryFactory.update(QPickup.pickup).set(QPickup.pickup.pickupStatus, PickupStatus.수거완료)
                    .where(QPickup.pickup.pickupId.eq(pickupId)).execute();
        }



//        리스트에 담아서 방번호를 이용해서 ++ 상태 없데이트 @



}
