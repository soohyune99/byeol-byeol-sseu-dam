package com.app.byeolbyeolsseudam.repository.badge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.badge.QBadgeDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.badge.QBadge.badge;

@Repository
@RequiredArgsConstructor
public class BadgeCustomRepositoryImpl implements BadgeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BadgeDTO> showBadgeList(){
        return jpaQueryFactory.select(new QBadgeDTO(
                badge.badgeId, badge.badgeName, badge.badgeInfo, badge.badgeFileName,
                badge.badgeFileUuid, badge.badgeFilePath))
                .from(badge)
                .orderBy(badge.badgeId.desc())
                .limit(18)
                .fetch();
    }
}
