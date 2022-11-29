package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.domain.PickupDTO;
import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.PickupAcceptRepository;
import com.app.byeolbyeolsseudam.repository.PickupRepository;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(false)
public class PickupAcceptTest {
    @Autowired
    private PickupAcceptRepository pickupAcceptRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PickupRepository pickupRepository;

    @Test
    public void saveTest(){
        PickupAcceptDTO pickupAcceptDTO = new PickupAcceptDTO();
        PickupDTO pickupDTO = new PickupDTO();
        Recyclable recyclable = new Recyclable();

        recyclable.setPetCount(10);
        recyclable.setGlassCount(10);


        pickupDTO.setPetCount(recyclable.getPetCount());
        pickupDTO.setGlassCount(recyclable.getGlassCount());
        pickupDTO.setPickupStatus(PickupStatus.수거대기중);
        pickupDTO.setPickupMessage("수거해줘");
        pickupDTO.setPickupAddress("우리집은비밀이야");

        Pickup pickup = pickupDTO.toEntity();

        pickupRepository.save(pickup);
        pickup.changeMember(memberRepository.findById(2L).get());

        PickupAccept pickupAccept2 = new PickupAccept();
        pickupAccept2.changeMember(memberRepository.findById(2L).get());
        pickupAccept2.changePickup(pickup);
        pickupAcceptRepository.save(pickupAccept2);

    }


//    @Test
//    public void updateTest(){
//        Optional <Pickup> updatePickupAccept = pickupRepository.findById(1L);
//
//
//        if (updatePickupAccept.isPresent()){
//            updatePickupAccept.get().update(PickupStatus.수거완료);
//        }
//
//
//
//   }

}
