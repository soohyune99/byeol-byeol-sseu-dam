package com.app.byeolbyeolsseudam.repository.banner;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;

@Repository
@RequiredArgsConstructor
public class BannerRepositoryImpl implements BannerCustomRepository{
    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BannerDTO> showList(){
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
    };

}
