package com.app.byeolbyeolsseudam.service.main;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import com.app.byeolbyeolsseudam.domain.banner.QBannerDTO;
import com.app.byeolbyeolsseudam.entity.banner.Banner;
import com.app.byeolbyeolsseudam.repository.banner.BannerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.app.byeolbyeolsseudam.entity.banner.QBanner.banner;


@Service
@RequiredArgsConstructor
@Slf4j
public class BannerService{

    private final BannerRepository bannerRepository;

    public Page<BannerDTO> show(Pageable pageable){

        List<BannerDTO> banners = bannerRepository.showList(pageable);
        final Page<BannerDTO> bannerPage = new PageImpl<>(banners, pageable, bannerRepository.findAll().size());
       return bannerPage;
    }

    public void save(Banner banner){
        bannerRepository.save(banner);
    }

    public void saveBanner(BannerDTO bannerDTO) {
        bannerRepository.save(bannerDTO.toEntity());
    }

    public BannerDTO selectById(Long bannerId){

        return bannerRepository.selectById(bannerId);
    }

    public void removeBanner(List<String> bannerIdstr){
        List<Long> bannerId = new ArrayList<>();
        bannerIdstr.stream().map(Long::parseLong).forEach(bannerId::add);
        bannerId.forEach(bannerRepository::deleteById);
    }

    public void updateBanner(BannerDTO bannerDTO, Long bannerId){
        bannerRepository.update(bannerDTO);
        Banner banner = bannerRepository.findById(bannerId).get();
        bannerRepository.save(banner);
    }

    public List<BannerDTO> showMainBanner(){
        return bannerRepository.showMainBanner();
    }
}
