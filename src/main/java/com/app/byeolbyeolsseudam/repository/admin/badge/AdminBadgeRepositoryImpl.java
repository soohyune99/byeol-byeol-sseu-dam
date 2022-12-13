package com.app.byeolbyeolsseudam.repository.admin.badge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.badge.QBadgeDTO;
import com.app.byeolbyeolsseudam.entity.badge.QBadge;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.badge.QBadge.badge;

@Repository
@RequiredArgsConstructor
public class AdminBadgeRepositoryImpl implements AdminBadgeCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<BadgeDTO> searchBadge(String keyword) {
        return jpaQueryFactory.select(new QBadgeDTO(
                badge.badgeId,
                badge.badgeName,
                badge.badgeInfo,
                badge.badgeFileName
        )).from(badge)
                .where(badge.badgeName.eq(keyword).or(badge.badgeInfo.eq(keyword)))
                .orderBy(badge.badgeId.desc())
                .fetch();
    }


    @Override
    public List<BadgeDTO> searchBadgePaging(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QBadgeDTO(
                badge.badgeId,
                badge.badgeName,
                badge.badgeInfo,
                badge.badgeFileName
        )).from(badge)
                .where(badge.badgeName.eq(keyword).or(badge.badgeInfo.eq(keyword)))
                .orderBy(badge.badgeId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<BadgeDTO> selectById(Long badgeId) {
        return jpaQueryFactory.select(new QBadgeDTO(
                badge.badgeId,
                badge.badgeName,
                badge.badgeInfo,
                badge.badgeFileName
        )).from(badge)
                .where(badge.badgeId.eq(badgeId))
                .fetch();
    }

    @Override
    public void update(BadgeDTO badgeDTO) {
        jpaQueryFactory.selectFrom(badge)
                .where(badge.badgeId.eq(badgeDTO.getBadgeId()))
                .fetchOne().update(badgeDTO);
    }


}
