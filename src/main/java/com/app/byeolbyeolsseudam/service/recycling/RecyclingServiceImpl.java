package com.app.byeolbyeolsseudam.service.recycling;

import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.pickup.PickupRepository;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class RecyclingServiceImpl implements RecyclingService {
    private final PickupRepository pickupRepository;
    private final MemberRepository memberRepository;


    /* 쓰담수거 _ 수거 신청 하기 */
    @Override
    public void recyclingSave(PickupDTO pickupDTO) {
        pickupDTO.setPickupStatus(PickupStatus.수거대기중);// 처음 신청한 순간 default

        Pickup pickup = pickupDTO.toEntity();
        pickup.changeMember(memberRepository.findById(pickupDTO.getMemberId()).get());

        pickupRepository.save(pickup);
    }

}
