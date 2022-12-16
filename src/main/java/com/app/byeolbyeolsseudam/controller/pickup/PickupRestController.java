package com.app.byeolbyeolsseudam.controller.pickup;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.service.pickup.PickupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pick/*")
@Slf4j
public class PickupRestController {
    private final PickupService pickupService;
    /* 픽업 REST Controller */
    @GetMapping(value = {"{size}","{size}/{sojaeji}"})
    public Page<PickupDTO> findListPickupStatusSojaeji(@PathVariable(value = "sojaeji", required = false)String searchSojaeji, @PathVariable("size")Integer size, @PageableDefault(page = 0, size = 6) Pageable pageable){
        pageable = PageRequest.of(0, size, Sort.Direction.DESC,"createdDate");

        Page<PickupDTO> pickupDTOS= pickupService.findListPickupStatusSojaeji(searchSojaeji, pageable);

        log.info("-------------------");
        log.info("!!! pageable !!!!!!  : "+ pageable);
        log.info("!!! getSort !!!!!!  : "+ pageable.getSort());
        log.info("!!! getPageSize !!!  : "+ pageable.getPageSize());
        log.info("!!! getOffset !!!!!  : "+ pageable.getOffset());
        log.info("!!! PageNumber !!!!  : "+ pageable.getPageNumber());
        log.info("-------------------");
        log.info("||| size ||||||||||||||| : "+ size);
        log.info("||| sojaeji |||||||||||| : " + searchSojaeji);
        log.info("||| pickupDTOS |||||||||||| : " + pickupDTOS);
        log.info("||| getTotalElements ||| : " + pickupDTOS.getTotalElements());
        log.info("-------------------");

        return pickupDTOS;
    }
}
