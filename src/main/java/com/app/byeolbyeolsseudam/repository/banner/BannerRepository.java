package com.app.byeolbyeolsseudam.repository.banner;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.app.byeolbyeolsseudam.entity.banner.Banner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;

public interface BannerRepository extends JpaRepository<Banner, Long>, BannerCustomRepository {


}
