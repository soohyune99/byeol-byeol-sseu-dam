package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;


@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PickupTest {

    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void saveMemberTest() {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setMemberLoginType(MemberLoginType.일반);
        memberDTO.setMemberCategory(MemberCategory.일반회원);
        memberDTO.setMemberName("대조영");
        memberDTO.setMemberPassword("4234234");
        memberDTO.setMemberPhone("010-2131-2131");
        memberDTO.setMemberEmail("12321@naver.com");
        memberDTO.setMemberPoint(2132131);
        memberDTO.setMemberProfileName("12313.png");
        memberDTO.setMemberProfilePath("12321312-test.png");
        memberDTO.setMemberProfileUuid("12312321-test.png");

        Member member = memberDTO.toEntity();
        memberRepository.save(member);
    }


    @Test
    public void savePickupTest(){
        PickupDTO pickupDTO = new PickupDTO();
        pickupDTO.setPetCount(5);
        pickupDTO.setGlassCount(10);
        pickupDTO.setPickupAddress("서울특별시 역삼역");
        pickupDTO.setPickupMessage("오후12시 이후 방문");
        pickupDTO.setPickupStatus(PickupStatus.수거대기중);
        pickupDTO.setMemberId(2L);

        Pickup pickup = pickupDTO.toEntity();
        pickup.changeMember(memberRepository.findById(pickupDTO.getMemberId()).get());
        pickupRepository.save(pickup);
    }

    @Test
    public void findTest() {
        Optional<Pickup> findPickup = pickupRepository.findById(3L);
        if (findPickup.isPresent()) {
            Assertions.assertThat(findPickup.get().getPickupId().equals(3L));
        }
    }

//    @Test
//    public void updateTest(){
//        Pickup findPickup = pickupRepository.findById(5L).get();
//        findPickup.update(PickupStatus.수거완료);
//    }

    @Test
    public void deleteTest(){
        pickupRepository.deleteById(2L);
    }



//    @Test
//    public void findTest(){
//        log.info("9번은 흥선대원군 : "+
//                pickupRepository.findById(8L).get().getMember().equals(memberRepository.findById(9L)));
//    }

//    @Test
//    public void savePickupTest(){
//        PickupDTO pickupDTO = new PickupDTO();
//        Recyclable recyclable = new Recyclable();
//        Pickup pickup = new Pickup();
//
//        pickupDTO.setPetCount(5);
//        pickupDTO.setGlassCount(10);
//
//        Pickup.builder().pickupAddress("서울특별시 역삼역")
//                .pickupMessage("3시 이후 수거해주세요")
//                .pickupStatus(PickupStatus.수거대기중)
//                .recyclable(recyclable)
//                .build();
//
//        pickup.getRecyclable().setPetCount(pickupDTO.getPetCount());
//        pickup.getRecyclable().setGlassCount(pickupDTO.getGlassCount());
//
//        Pickup pickup = pickupDTO.toEntity();
//        pickupRepository.save(pickup);
//    }

//    @Test
//    public void savePickupTest(){
//        PickupDTO pickupDTO = new PickupDTO();
//
//        pickupDTO.setPetCount(5);
//        pickupDTO.setGlassCount(10);
//        pickupDTO.setPickupAddress("경기도 수원시 장안구");
//        pickupDTO.setPickupMessage("문 앞에 보관중입니다");
//        pickupDTO.setPickupStatus(PickupStatus.수거대기중);
//        pickupDTO.setMemberId(2L);
//        pickupDTO.setCreatedDate(LocalDateTime.now());
//        pickupDTO.setPickupId(null);
////        Pickup pickup = pickupDTO.toEntity();
////        pickupRepository.save(pickup);
////        Pickup pickup = pickupDTO.toEntity();
//        pickupRepository.save(pickupDTO.toEntity());
//    }




//        PickupDTO pickupDTO = new PickupDTO();
//        Pickup pickup = new Pickup();
//        Recyclable recyclable = new Recyclable();
//
//        recyclable.setPetCount(10);
//        recyclable.setGlassCount(10);
//
//
//        pickup.builder().member(member)
//                .glassCount(5)
//                .petCount(5)
//                .pickupAddress("서울강동구")
//                .pickupMessage("살살수거해주세요")
//                .pickupStatus(PickupStatus.수거중).build();
//
//        assertThat(pickup.getPickupAddress()).isEqualTo("서울강동구");
//
//        pickupDTO.setGlassCount(5);
//        pickupDTO.setPetCount(5);
//        pickupDTO.setPickupAddress("서울강동구");
//        pickupDTO.setPickupMessage("살살수거해주세요");
//        pickupDTO.setPickupStatus(PickupStatus.수거중);
//        pickupDTO.setMember(memberRepository.findByMemberEmail("sej07@naver.com").get(0));
//
//        pickupRepository.save(pickupDTO.toEntity());
//
//
//    }

//    @Test
//    public void findTest(){
//        assertThat(pickupRepository.findById(3L).get().getMember().getMemberName()).isEqualTo("은지");
//    }

//    @Test
//    public void updateTest(){
//        Optional<Pickup> updatePickup = pickupRepository.findById(3L);
//
//        Recyclable recyclable = new Recyclable();
//
//        recyclable.setPetCount(10);
//        recyclable.setGlassCount(10);
//
//        if(updatePickup.isPresent()){
//            updatePickup.get().update(recyclable, "강동","안녕",PickupStatus.수거완료);
//        }



//    }
//
//    @Test
//    public void deleteTest(){
//        Optional<Pickup> deletePickup = pickupRepository.findById(3L);
//
//        if(deletePickup.isPresent()){
//            pickupRepository.delete(deletePickup.get());
//        }
//    }



}

