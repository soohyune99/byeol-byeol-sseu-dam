package com.app.byeolbyeolsseudam.service.main;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;


@Service
@RequiredArgsConstructor
public class BannerService implements MainService{
    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<BannerDTO> showList(){
        return jpaQueryFactory.select(new QBannerDTO(
                banner.bannerId,
                banner.bannerName,
                banner.bannerUuid,
                banner.bannerPath
        )).from(banner)
                .orderBy(banner.bannerId.desc()).fetch();
    };
}
