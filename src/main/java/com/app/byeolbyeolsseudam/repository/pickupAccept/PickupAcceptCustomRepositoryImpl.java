package com.app.byeolbyeolsseudam.repository.pickupAccept;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickup.QPickupDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public Page<PickupDTO> findListPickupStatusSojaeji(String sojaeji, Pageable pageable) {

        List<PickupDTO> pickupDTOS = jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
//                QPickupAccept.pickupAccept.member.memberId, /* 기사님 멤버아이디 */
//                QPickupAccept.pickupAccept.member.memberName, /* 기사님 이름 */
                pickup.createdDate
        ))
                .from(pickup)
                .orderBy(pickup.createdDate.desc())
                .where(
                        pickup.pickupStatus.eq(PickupStatus.수거대기중), // 수거대기중 리스트에서 사용
                        sojaejiContains(sojaeji) // 소재지가 해당 address에 일치하는지 여부 확인
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory.select(new QPickupDTO(
                pickup.pickupId,
                pickup.recyclable.petCount,
                pickup.recyclable.glassCount,
                pickup.pickupAddress,
                pickup.pickupMessage,
                pickup.pickupStatus,
                pickup.member.memberId,
                pickup.member.memberName,
//                QPickupAccept.pickupAccept.member.memberId, /* 기사님 멤버아이디 */
//                QPickupAccept.pickupAccept.member.memberName, /* 기사님 이름 */
                pickup.createdDate
        ))
                .from(pickup)
                .where(
                        pickup.pickupStatus.eq(PickupStatus.수거대기중), // 수거대기중 리스트에서 사용
                        sojaejiContains(sojaeji)
                ).fetch().size();

        return new PageImpl<>(pickupDTOS, pageable, total);

    }

    /* sojaeji(소재지) 일치 여부 확인 메소드 _ 해당되는 내역이 없다면 전체 리스트를 제공한다 */
    private BooleanExpression sojaejiContains(String sojaeji) {
        return StringUtils.hasText(sojaeji) ? pickup.pickupAddress.contains(sojaeji) : null;
    }

    /* ---------------------------------------------------------------------------------------------------------- */

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

    /* ------------------------------------------------------------------------------------------------------------------------------------------------ */

    /* 수거 대기중,수거중,수거완료 _ 상세 페이지 이동 / 클릭시 디테일 이동( 픽업엡셋아이디로 조회 필요 신청하는 순간에는 기사님 멤버아이디(세션에있는것) 가져오기 */
    @Override
    public PickupDTO findPickupId(Long pickupId) {
        return jpaQueryFactory.select(new QPickupDTO(
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
        ))
                .from(pickup)
                .orderBy(pickup.createdDate.desc())
                .fetchOne();
    }

    /* ------------------------------------------------------------------------------------------------------------------------------------------------ */

    /* 수거대기중 상세페이지에서 _ 수락하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기 */
    /* 수거중 상세페이지에서 _ 수거완료하기 버튼 누르면 실행 /  세션에 멤버아이디 가져오기*/

    //  =>  픽업엔티티에 update메소드가 있기 때문에 서비스 에서 사용 후 컨트롤러에서 사용하면됨. 따로 만들필요 없음.

    /* ------------------------------------------------------------------------------------------------------------------------------------------------ */

}
