package com.app.byeolbyeolsseudam.repository.banner;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BannerRepositoryImpl implements BannerCustomRepository{
    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BannerDTO> showList(Pageable pageable){
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerName,
                banner.bannerPath,
                banner.bannerUuid
        )).from(banner)
                .orderBy(banner.bannerId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    };

    @Override
    public BannerDTO selectById(Long bannerId) {
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerName,
                banner.bannerPath,
                banner.bannerUuid
        )).from(banner)
                .where(banner.bannerId.eq(bannerId))
                .fetchOne();
    }


    @Override
    public void update(BannerDTO bannerDTO) {
        jpaQueryFactory.selectFrom(banner)
                .where(banner.bannerId.eq(bannerDTO.getBannerId()))
                .fetchOne().update(bannerDTO);
    }

    @Override
    public List<BannerDTO> showMainBanner() {
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerName,
                banner.bannerPath,
                banner.bannerUuid
        )).from(banner)
                .orderBy(banner.bannerId.desc())
                .offset(0)
                .limit(7)
                .fetch();
    }
}
