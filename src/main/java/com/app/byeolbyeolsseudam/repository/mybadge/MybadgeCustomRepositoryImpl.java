package com.app.byeolbyeolsseudam.repository.mybadge;

import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.QMybadgeDTO;
import com.app.byeolbyeolsseudam.entity.mybadge.QMybadge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.mybadge.QMybadge.mybadge;

@Repository
@RequiredArgsConstructor
public class MybadgeCustomRepositoryImpl implements MybadgeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MybadgeDTO> selectMybadges(){
        return jpaQueryFactory.select(new QMybadgeDTO(mybadge.mybadgeId,
                mybadge.member.memberId, mybadge.badge.badgeId, mybadge.createdDate))
                .from(mybadge)
                .fetch();
    }
}
