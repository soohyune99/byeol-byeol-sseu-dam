package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.MemberDTO;
import com.app.byeolbyeolsseudam.domain.PickupDTO;
import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.PickupRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.OPTIONAL;
import static org.junit.jupiter.api.Assertions.*;

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
    public void saveTest(){


        PickupDTO pickupDTO = new PickupDTO();
        MemberDTO memberDTO = new MemberDTO();
        Pickup pickup = new Pickup();
        Recyclable recyclable = new Recyclable();

        recyclable.setPetCount(10);
        recyclable.setGlassCount(10);

//        memberDTO.setMemberLoginType(MemberLoginType.네이버);
//        memberDTO.setMemberCategory(MemberCategory.일반회원);
//        memberDTO.setMemberName("장선홍");
//        memberDTO.setMemberEmail("jsh5060@dreamwiz.com");
//        memberDTO.setMemberPassword("1234");
//        memberDTO.setMemberAddress("서울강동");
//        memberDTO.setMemberPhone("01012345678");
//        memberDTO.setMemberProfileFile("test.png");

//        pickup.builder().member(member)
//                .glassCount(5)
//                .petCount(5)
//                .pickupAddress("서울강동구")
//                .pickupMessage("살살수거해주세요")
//                .pickupStatus(PickupStatus.수거중).build();
//
//        assertThat(pickup.getPickupAddress()).isEqualTo("서울강동구");

        pickupDTO.setGlassCount(5);
        pickupDTO.setPetCount(5);
        pickupDTO.setPickupAddress("서울강동구");
        pickupDTO.setPickupMessage("살살수거해주세요");
        pickupDTO.setPickupStatus(PickupStatus.수거중);
        pickupDTO.setMember(memberRepository.findByMemberEmail("sej07@naver.com").get(0));

        pickupRepository.save(pickupDTO.toEntity());


    }

    @Test
    public void findTest(){
        assertThat(pickupRepository.findById(3L).get().getMember().getMemberName()).isEqualTo("은지");
    }

    @Test
    public void updateTest(){
        Optional<Pickup> updatePickup = pickupRepository.findById(3L);

        Recyclable recyclable = new Recyclable();

        recyclable.setPetCount(10);
        recyclable.setGlassCount(10);

        if(updatePickup.isPresent()){
            updatePickup.get().update(recyclable, "강동","안녕",PickupStatus.수거완료);
        }



    }

    @Test
    public void deleteTest(){
        Optional<Pickup> deletePickup = pickupRepository.findById(3L);

        if(deletePickup.isPresent()){
            pickupRepository.delete(deletePickup.get());
        }
    }



}
