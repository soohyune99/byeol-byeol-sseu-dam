package com.app.byeolbyeolsseudam.repository.admin.pickup;

import com.app.byeolbyeolsseudam.domain.pickupAccept.PickupAcceptDTO;
import com.app.byeolbyeolsseudam.domain.pickupAccept.QPickupAcceptDTO;
import com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.app.byeolbyeolsseudam.entity.pickupAccept.QPickupAccept.pickupAccept;

@Repository
@RequiredArgsConstructor
public class AdminPickupAcceptRepositoryIml implements AdminPickupAcceptCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;


}
