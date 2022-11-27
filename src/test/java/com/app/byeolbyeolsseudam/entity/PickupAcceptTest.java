package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.repository.MemberRepository;
import com.app.byeolbyeolsseudam.repository.PickupAcceptRepository;
import com.app.byeolbyeolsseudam.repository.PickupRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

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


        pickupAcceptDTO.setPickup(pickupRepository.findById(4L).get());
        pickupAcceptDTO.setMember(memberRepository.findByMemberEmail("sej07@naver.com").get(0));

        pickupAcceptRepository.save(pickupAcceptDTO.toEntity());

    }

}
