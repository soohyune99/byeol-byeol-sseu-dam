package com.app.byeolbyeolsseudam.repository.pickupAccept;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.pickup.QPickup.pickup;


@Repository
@RequiredArgsConstructor
public class PickupAcceptCustomRepositoryImpl implements PickupAcceptCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    // 참고사항
    // enum PickupStatus  :  수거대기중, 신청취소, 수거중, 수거완료
    // 신청 취소는 관리자에서만 가능

    /* 수거 대기중 리스트 / 수거 신청페이지 _ 해당되는 지역에 따라 동적쿼리 사용*/
    /* 동적 쿼리 - 수거대기중인 상태에서 소재지 키워드 Contains 여부 확인 */
    @Override
    public List<PickupDTO> findListPickupStatusSojaeji(String sojaeji) {

        List<PickupDTO> pickupDTOS = jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
                QPickupAccept.pickupAccept.member.memberId, /* 기사님 멤버아이디 */
                QPickupAccept.pickupAccept.member.memberName, /* 기사님 이름 */
                pickup.createdDate
        )).from(pickup)
                .where(
                        pickup.pickupStatus.eq(PickupStatus.수거대기중), // 수거대기중 리스트에서 사용
                        sojaejiContains(sojaeji) // 소재지가 해당 address에 일치하는지 여부 확인
                )
                .orderBy(pickup.createdDate.desc())
                .fetch();

        return pickupDTOS;

    }

    /* sojaeji(소재지) 일치 여부 확인 메소드 _ 해당되는 내역이 없다면 전체 리스트를 제공한다 */
    private BooleanExpression sojaejiContains(String sojaeji){
        return StringUtils.hasText(sojaeji) ? pickup.pickupAddress.contains(sojaeji) : null;
    }

    /* 수거중 리스트 _ 수거현황 페이지 _ 기사님이 수락한 리스트 */
    @Override
    public List<PickupDTO> findListPickupStatusIng(Long memberId) {
        List<PickupDTO> IngPickupDTOS = jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
                QPickupAccept.pickupAccept.member.memberId, /* 기사님 멤버아이디 */
                QPickupAccept.pickupAccept.member.memberName, /* 기사님 이름 */
                pickup.createdDate
        )).from(pickup)
                .where(
                        QPickupAccept.pickupAccept.member.memberId.eq(memberId),// 기사님 멤버아이디
                        pickup.pickupStatus.eq(PickupStatus.수거중) // 수거 대기중 리스트에서 사용
                )
                .orderBy(pickup.createdDate.desc())
                .fetch();
        return IngPickupDTOS;
    }


    /* 수거완료 리스트 _ 수거완료 페이지 _ 기사님이 수거 완료한 리스트 */
    @Override
    public List<PickupDTO> findListPickupStatusFinish(Long memberId) {
        List<PickupDTO> finishPickupDTOS = jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
                QPickupAccept.pickupAccept.member.memberId,
                QPickupAccept.pickupAccept.member.memberName,
                pickup.createdDate
        )).from(pickup)
                .where(
                        QPickupAccept.pickupAccept.member.memberId.eq(memberId),// 기사님 멤버아이디 일치하는 곳에서
                        pickup.pickupStatus.eq(PickupStatus.수거완료) // 수거 완료 리스트에서 사용
                )
                .orderBy(pickup.createdDate.desc())
                .fetch();
        return finishPickupDTOS;
    }

    /* 수거 대기중,수거중,수거완료 _ 상세 페이지 이동 / 클릭시 디테일 이동( 픽업엡셋아이디로 조회 필요 신청하는 순간에는 기사님 멤버아이디(세션에있는것) 가져오기 */
    @Override
    public PickupDTO findPickupId(Long pickupId) {
        return null;
    }


    /* 수거대기중 상세페이지에서 _ 수락하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기 */
    @Override
    public void savePickupAccept(Long pickupId, Long memberId) {

    }

    /* 수거중 상세페이지에서 _ 수거완료하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기*/
    @Override
    public void CompletePickup(Long pickupId, Long memberId) {

    }




    /*-----------------------------------------------------------------------------------------------*/

//
//
//
//    @Override
//    public List<PickupDTO> findAllByPickupStatus() {
//
//        return queryFactory.select(new QPickupDTO(
//                QPickup.pickup.pickupId,
//                QPickup.pickup.recyclable.petCount,
//                QPickup.pickup.recyclable.glassCount,
//                QPickup.pickup.pickupAddress,
//                QPickup.pickup.member.memberName,
//                QPickup.pickup.pickupStatus,
//                QPickup.pickup.member.memberId,
//                QPickup.pickup.createdDate
//        )).from(QPickup.pickup)
//                .where(QPickup.pickup.pickupStatus.eq(PickupStatus.수거대기중))
//                .orderBy(QPickup.pickup.createdDate.desc())
//                .fetch();
//
//    }
//
//
//
//    @Override
//    public List<PickupDTO> findAllByMyPickup(Long memberId) {
//
//        return queryFactory.select(new QPickupDTO(
//                QPickup.pickup.pickupId,
//                QPickup.pickup.recyclable.petCount,
//                QPickup.pickup.recyclable.glassCount,
//                QPickup.pickup.pickupAddress,
//                QPickup.pickup.member.memberName,
//                QPickup.pickup.pickupStatus,
//                QPickup.pickup.member.memberId,
//                QPickup.pickup.createdDate
//        ))
//                .from(QPickup.pickup, pickupAccept)
//                .where(
//                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거중),
//                        pickupAccept.member.memberId.eq(memberId)
//                )
//                .orderBy(QPickup.pickup.createdDate.desc())
//                .distinct()
//                .fetch();
//
////.join(QPickupAccept.pickupAccept)
////                .on(QPickup.pickup.pickupStatus.eq(PickupStatus.수거중))
////        QPickup.pickup.pickupStatus.eq(PickupStatus.수거중),
//    }
//
//
//    @Override
//    public List<PickupDTO> findAllByComplete(Long memberId) {
//
//        return queryFactory.select(new QPickupDTO(
//                QPickup.pickup.pickupId,
//                QPickup.pickup.recyclable.petCount,
//                QPickup.pickup.recyclable.glassCount,
//                QPickup.pickup.pickupAddress,
//                QPickup.pickup.member.memberName,
//                QPickup.pickup.pickupStatus,
//                QPickup.pickup.member.memberId,
//                QPickup.pickup.createdDate
//        ))
//                .from(QPickup.pickup, pickupAccept)
//                .where(
//                        QPickup.pickup.pickupStatus.eq(PickupStatus.수거완료),
//                        pickupAccept.member.memberId.eq(memberId)
//                )
//                .orderBy(QPickup.pickup.createdDate.desc())
//                .distinct()
//                .fetch();
//    }
//
//    @Override
//    public PickupDTO find(Long pickupId){
//
//        return queryFactory.select(new QPickupDTO(
//                QPickup.pickup.pickupId,
//                QPickup.pickup.recyclable.petCount,
//                QPickup.pickup.recyclable.glassCount,
//                QPickup.pickup.pickupAddress,
//                QPickup.pickup.member.memberName,
//                QPickup.pickup.pickupStatus,
//                QPickup.pickup.member.memberId,
//                QPickup.pickup.createdDate
//        ))
//                .from(QPickup.pickup, pickupAccept)
//                .where(
//                        QPickup.pickup.pickupId.eq(pickupId)
//                ).fetchOne();
//    }
//
//
////    @Override
////    public Page<PickupDTO> findByPickupName(Pageable pageable){
////
////        List<PickupDTO> pickups = queryFactory.selectFrom(QPickup.pickup)
////                .where(QPickup.pickup.member.memberName.eq("장선홍"))
////                .orderBy(QPickup.pickup.pickupId.desc())
////                .offset(pageable.getOffset())
////                .limit(pageable.getPageSize())
////                .fetch();
////
////        int total = queryFactory.selectFrom(QPickup.pickup)
////                .where(QPickup.pickup.member.memberName.eq("장선홍"))
////                .fetch().size();
////
////        return new PageImpl(pickups, pageable, total);
////
////    }
//
//
////        픽업 수락하기 (PickUp Accept 저장하기)
////        @Override
////        public void Accept(Long memberId){
////            pickupRepository.findById(1L).get().getMember().getMemberId();
////
////            jpaQueryFactory.insert(pickupAccept)
////                    .columns(pickupAccept.member.memberId, pickupAccept.pickup.pickupId)
////                    .values(pickupRepository.findById(1L).get().getMember().getMemberId(),
////                            memberId)
////                    .
////
////
////        }
//
//    //        상태 진행중으로 업데이트
//    @Override
//    public void StatusUpdate(Long pickupId){
//        jpaQueryFactory.update(QPickup.pickup).set(QPickup.pickup.pickupStatus, PickupStatus.수거중)
//                .where(QPickup.pickup.pickupId.eq(pickupId)).execute();
//
//    }
//
//
//    //        완료 업데이트
//    @Override
//    public void Complete(Long pickupId){
//        jpaQueryFactory.update(QPickup.pickup).set(QPickup.pickup.pickupStatus, PickupStatus.수거완료)
//                .where(QPickup.pickup.pickupId.eq(pickupId)).execute();
//    }
//
//
//
////        리스트에 담아서 방번호를 이용해서 ++ 상태 없데이트 @
//


}
