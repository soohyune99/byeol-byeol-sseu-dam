package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.repository.admin.pickup.AdminPickupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminPickService {
    private final AdminPickupRepository adminPickupRepository;

    public Page<PickupDTO> showList(Pageable pageable){
        List<PickupDTO> pickups=  adminPickupRepository.showPickupList(pageable);
        final Page<PickupDTO> pickupList = new PageImpl<>(pickups, pageable, adminPickupRepository.findAll().size());

        return pickupList;
    }

    public PickupDTO selectById(String pickupIdstr){
        Long pickupId = Long.parseLong(pickupIdstr);
        return adminPickupRepository.selectById(pickupId);

    }
}
