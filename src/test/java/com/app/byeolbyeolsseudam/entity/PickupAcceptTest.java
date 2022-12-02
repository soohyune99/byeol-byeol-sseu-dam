package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.embaddable.Recyclable;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.entity.pickup.QPickup;
import com.app.byeolbyeolsseudam.entity.pickupAccept.PickupAccept;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptCustomRepository;
import com.app.byeolbyeolsseudam.repository.pickupAccept.PickupAcceptRepository;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.pickup.QPickup.*;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class PickupAcceptTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private PickupAcceptRepository pickupAcceptRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PickupRepository pickupRepository;

    @Autowired
    private PickupAcceptCustomRepository pickupAcceptCustomRepository;

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

        pickup.changeMember(memberRepository.findById(1L).get());
        pickupRepository.save(pickup);



        PickupAccept pickupAccept2 = new PickupAccept();
        pickupAccept2.changeMember(memberRepository.findById(2L).get());
        pickupAccept2.changePickup(pickup);
        pickupAcceptRepository.save(pickupAccept2);

    }

    @Test
    public void findTest(){
        List<Pickup> pickups = jpaQueryFactory.selectFrom(pickup)
                .where(pickup.pickupStatus.eq(PickupStatus.수거대기중))
                .fetch();

        pickups.stream().map(Pickup::toString).forEach(log::info);

    }

    @Test
    public void findTest2(){
        jpaQueryFactory.selectFrom(pickup).where(pickup.pickupStatus.eq(PickupStatus.수거대기중))
                .orderBy(pickup.pickupId.desc()).fetch()
                .stream().map(Pickup::toString).forEach(log::info);

    }


    @Test
    public void updateTest(){
        jpaQueryFactory.update(pickup).set(pickup.pickupStatus, PickupStatus.수거중)
                .where(QPickupAccept.pickupAccept.member.memberId.eq(2L)).execute();

    }


    @Test
    public void updateTest2(){
        jpaQueryFactory.update(QPickupAccept.pickupAccept).set(QPickupAccept.pickupAccept.pickup.pickupId, 3L)
                .where(QPickupAccept.pickupAccept.pickupAcceptId.eq(12L)).execute();

    }



    @Test
    public void deleteTest(){
        Optional<PickupAccept> deletePickupAccept = pickupAcceptRepository.findById(1L);

        if (deletePickupAccept.isPresent()){
            pickupAcceptRepository.delete(deletePickupAccept.get());

        }


    }

    @Test
    public void deleteTest2(){
        jpaQueryFactory.delete(pickup).where(pickup.pickupId.eq(6L)).execute();
    }


    @Test
    public void findTest3(){
        pickupAcceptCustomRepository.findAllByPickupStatus().stream().map(PickupDTO::toString).forEach(log::info);

    }

    @Test
    public void findTest4(){
        pickupAcceptCustomRepository.findAllByMyPickup(2L).stream().map(PickupDTO::toString).forEach(log::info);


    }


}
