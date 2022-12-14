package com.app.byeolbyeolsseudam.repository.admin.spot;

import com.app.byeolbyeolsseudam.domain.spot.QSpotDTO;
import com.app.byeolbyeolsseudam.domain.spot.SpotDTO;
import com.app.byeolbyeolsseudam.entity.spot.QSpot;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.spot.QSpot.spot;

@Repository
@RequiredArgsConstructor
public class AdminSpotRepositoryImpl implements AdminSpotCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<SpotDTO> searchSpot(String keyword) {
        return jpaQueryFactory.select(new QSpotDTO(
                spot.spotId,
                spot.spotName,
                spot.spotAddress,
                spot.spotNumber,
                spot.course.courseId
        )).from(spot)
                .where(spot.spotName.contains(keyword))
                .orderBy(spot.spotId.desc())
                .fetch();
    }

    @Override
    public List<SpotDTO> searchSpotPaging(String keyword, Pageable pageable) {
        return jpaQueryFactory.select(new QSpotDTO(
                spot.spotId,
                spot.spotName,
                spot.spotAddress,
                spot.spotNumber,
                spot.course.courseId
        )).from(spot)
                .where(spot.spotName.contains(keyword))
                .orderBy(spot.spotId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public SpotDTO selectById(Long spotId) {
        return jpaQueryFactory.select(new QSpotDTO(
                spot.spotId,
                spot.spotName,
                spot.spotAddress,
                spot.spotNumber,
                spot.spotQrName,
                spot.spotQrPath,
                spot.spotQrUuid
        )).from(spot)
                .where(spot.spotId.eq(spotId))
                .fetchOne();
    }

    @Override
    public void update(SpotDTO spotDTO) {
        jpaQueryFactory.selectFrom(spot)
                .where(spot.spotId.eq(spotDTO.getSpotId()))
                .fetchOne().update(spotDTO);
    }
}
