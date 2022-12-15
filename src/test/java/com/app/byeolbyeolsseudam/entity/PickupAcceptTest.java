package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.QPickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptCustomRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptRepository;
import com.app.byeolbyeolsseudam.service.pickup.PickupService;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.pickup.QPickup.pickup;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PickupAcceptTest {
//    @Autowired
//    private JPAQueryFactory jpaQueryFactory;
//
//    @Autowired
//    private PickupAcceptRepository pickupAcceptRepository;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    @Autowired
//    private PickupRepository pickupRepository;
//
//    @Autowired
//    private PickupAcceptCustomRepository pickupAcceptCustomRepository;
//
//    @Autowired
//    private PickupService2 pickupService2;



    @Autowired
    private PickupService pickupService;

    @Autowired
    private  PickupAcceptRepository pickupAcceptRepository;

    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    PickupAcceptCustomRepository pickupAcceptCustomRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;
//    @Test
//    public void findListPickupStatusSojaejiTest(){
//        Pageable pageable = PageRequest.of(0,12);
//        log.info("전체 리스트 : "+ pickupAcceptRepository.findListPickupStatusSojaeji("경남", pageable).getSize());
//
//        log.info("서울 지역 : " + pickupAcceptRepository.findListPickupStatusSojaeji("서울", pageable).getSize());
//    }
//
    @Test
    public void test(){
//        log.info(pickupService.updatePickupStatusIng(182L).toString());
//        log.info(pickupAcceptCustomRepository.findListPickupStatusIng(192L).toString());
        Pageable pageable = PageRequest.of(0,12, Sort.Direction.DESC,"createdDate");
//        log.info(pickupService.findListPickupStatusSojaeji("충남", pageable).toString());


        List<PickupAcceptDTO> pickupAcceptDTOS = jpaQueryFactory.select(new QPickupAcceptDTO(
                QPickupAccept.pickupAccept.pickupAcceptId, // 수락번호
                QPickupAccept.pickupAccept.pickup.pickupId, // 신청번호
                QPickupAccept.pickupAccept.pickup.recyclable.petCount, // 페트병
                QPickupAccept.pickupAccept.pickup.recyclable.glassCount, // 유리병
                QPickupAccept.pickupAccept.pickup.pickupAddress, // 수거할 주소
                QPickupAccept.pickupAccept.pickup.pickupMessage, // 수거 메세지
                QPickupAccept.pickupAccept.pickup.pickupStatus, // 수거상태
                QPickupAccept.pickupAccept.member.memberId, // 기사님
                QPickupAccept.pickupAccept.member.memberName, // 기사님
                QPickupAccept.pickupAccept.createdDate, // 수거 수락일
                QPickupAccept.pickupAccept.updatedDate, // 수거 완료일
                QPickupAccept.pickupAccept.pickup.member.memberId, // 신청자 아이디
                QPickupAccept.pickupAccept.pickup.member.memberName, // 신청자 이름
                QPickupAccept.pickupAccept.pickup.member.memberProfilePath, //신청자 프로필사진
                QPickupAccept.pickupAccept.pickup.member.memberPhone // 신청자 핸드폰번호
        )).from(QPickupAccept.pickupAccept)
                .where(
                        pickup.pickupStatus.eq(PickupStatus.수거대기중) // 수거대기중 리스트에서 사용
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(pickup.createdDate.desc())
                .fetch();

        pickupAcceptDTOS.stream().map(PickupAcceptDTO::toString).forEach(log::info);

    }
















//    @Test
//    public void saveTest(){
//        PickupAcceptDTO pickupAcceptDTO = new PickupAcceptDTO();
//        PickupDTO pickupDTO = new PickupDTO();
//        Recyclable recyclable = new Recyclable();
//
//        recyclable.setPetCount(10);
//        recyclable.setGlassCount(10);
//
//            pickupDTO.setPetCount(recyclable.getPetCount());
//            pickupDTO.setGlassCount(recyclable.getGlassCount());
//            pickupDTO.setPickupStatus(PickupStatus.수거대기중);
//            pickupDTO.setPickupMessage("수거해줘");
//            pickupDTO.setPickupAddress("서울 강동구 길동 1234");
//
//            Pickup pickup = pickupDTO.toEntity();
//
//            pickup.changeMember(memberRepository.findById(1L).get());
//            pickupRepository.save(pickup);
//        }
//
//
//
////        PickupAccept pickupAccept2 = new PickupAccept();
////        pickupAccept2.changeMember(memberRepository.findById(2L).get());
////        pickupAccept2.changePickup(pickup);
////        pickupAcceptRepository.save(pickupAccept2);
//
//
//
//    @Test
//    public void findTest(){
//        List<Pickup> pickups = jpaQueryFactory.selectFrom(pickup)
//                .where(pickup.pickupStatus.eq(PickupStatus.수거대기중))
//                .fetch();
//
//        pickups.stream().map(Pickup::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void findTest2(){
//        jpaQueryFactory.selectFrom(pickup).where(pickup.pickupStatus.eq(PickupStatus.수거대기중))
//                .orderBy(pickup.pickupId.desc()).fetch()
//                .stream().map(Pickup::toString).forEach(log::info);
//
//    }
//
//
//    @Test
//    public void updateTest(){
//        jpaQueryFactory.update(pickup).set(pickup.pickupStatus, PickupStatus.수거중)
//                .where(QPickupAccept.pickupAccept.member.memberId.eq(134L)).execute();
//
//    }
//
//
//    @Test
//    public void updateTest2(){
//        jpaQueryFactory.update(QPickupAccept.pickupAccept).set(QPickupAccept.pickupAccept.pickup.pickupId, 3L)
//                .where(QPickupAccept.pickupAccept.pickupAcceptId.eq(12L)).execute();
//
//    }
//
//
//
//    @Test
//    public void deleteTest(){
//        Optional<PickupAccept> deletePickupAccept = pickupAcceptRepository.findById(1L);
//
//        if (deletePickupAccept.isPresent()){
//            pickupAcceptRepository.delete(deletePickupAccept.get());
//
//        }
//
//
//    }
//
//    @Test
//    public void deleteTest2(){
//        jpaQueryFactory.delete(pickup).where(pickup.pickupId.eq(6L)).execute();
//    }


//    @Test
//    public void findTest3(){
//        pickupAcceptCustomRepository.findAllByPickupStatus().stream().map(PickupDTO::toString).forEach(log::info);
//
//    }
//
//    @Test
//    public void findTest4(){
//        pickupAcceptCustomRepository.findAllByMyPickup(2L).stream().map(PickupDTO::toString).forEach(log::info);
//
//
//    }

//    @Test
//    public void Service(){
//        pickupService2.getPickupList().stream().map(PickupDTO::toString).forEach(log::info);
//    }
//
//    @Test
//    public void Service2(){
////            pickupService2.UpdateAndSave( 3L, 2L);
////        pickupAcceptRepository.findAllByMemberMemberIdAndPickup_PickupStatus_수거중(2,)
//
////        pickupService2.CompleteAndSave(7L);
//
//        log.info(pickupService2.getMyPickupList(2L).toString());
//    }



}
