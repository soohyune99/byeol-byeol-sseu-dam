package com.app.byeolbyeolsseudam.repository.mybadge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.course.CourseDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.QMybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mycourse.MycourseDTO;
import com.app.byeolbyeolsseudam.entity.mybadge.QMybadge;
import com.app.byeolbyeolsseudam.entity.mycourse.QMycourse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.board.QBoard.board;
import static com.app.byeolbyeolsseudam.entity.mybadge.QMybadge.mybadge;
import static com.app.byeolbyeolsseudam.entity.mycourse.QMycourse.mycourse;

@Repository
@RequiredArgsConstructor
public class MybadgeCustomRepositoryImpl implements MybadgeCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MybadgeDTO> selectMybadges(Long memberId){
        return jpaQueryFactory.select(new QMybadgeDTO(mybadge.mybadgeId,
                mybadge.member.memberId, mybadge.badge.badgeId, mybadge.createdDate))
                .from(mybadge)
                .where(mybadge.member.memberId.eq(memberId))
                .fetch();
    }
}
