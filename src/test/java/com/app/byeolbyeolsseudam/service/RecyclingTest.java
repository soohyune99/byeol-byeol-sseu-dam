package com.app.byeolbyeolsseudam.service;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.service.recycling.RecyclingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class RecyclingTest {
    @Autowired
    RecyclingService recyclingService;

    @Test
    public void saveTest(){
        PickupDTO pickupDTO = new PickupDTO();
        pickupDTO.setMemberId(134L);
        pickupDTO.setGlassCount(10);
        pickupDTO.setPetCount(15);
        pickupDTO.setPickupAddress("경기도 수원시");
        pickupDTO.setPickupMessage("문 앞에 보관");

        recyclingService.recyclingSave(pickupDTO);

    }



}
