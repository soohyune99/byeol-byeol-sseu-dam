package com.app.byeolbyeolsseudam.service.main;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.app.byeolbyeolsseudam.entity.banner.Banner;
import com.app.byeolbyeolsseudam.repository.banner.BannerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;


@Service
@RequiredArgsConstructor
public class BannerService{

    private final BannerRepository bannerRepository;

    public List<BannerDTO> show(){
       return bannerRepository.showList();
    }

    public void save(Banner banner){
        bannerRepository.save(banner);
    }

    public void delete(Banner banner){bannerRepository.delete(banner);}

}
