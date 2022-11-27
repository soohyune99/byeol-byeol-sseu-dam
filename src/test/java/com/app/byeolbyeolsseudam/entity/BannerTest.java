package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BannerDTO;
import com.app.byeolbyeolsseudam.repository.BannerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BannerTest {
    @Autowired
    private BannerRepository bannerRepository;

    @Test
    public void saveTest(){
        BannerDTO bannerDTO1 = new BannerDTO();
        BannerDTO bannerDTO2 = new BannerDTO();
        BannerDTO bannerDTO3 = new BannerDTO();
        BannerDTO bannerDTO4 = new BannerDTO();
        BannerDTO bannerDTO5 = new BannerDTO();
        BannerDTO bannerDTO6 = new BannerDTO();
        BannerDTO bannerDTO7 = new BannerDTO();

        bannerDTO1.setBannerName("banner1.png");
        bannerDTO2.setBannerName("banner2.png");
        bannerDTO3.setBannerName("banner3.png");
        bannerDTO4.setBannerName("banner4.png");
        bannerDTO5.setBannerName("banner5.png");
        bannerDTO6.setBannerName("banner6.png");
        bannerDTO7.setBannerName("banner7.png");

        bannerRepository.save(bannerDTO1.toEntity());
        bannerRepository.save(bannerDTO2.toEntity());
        bannerRepository.save(bannerDTO3.toEntity());
        bannerRepository.save(bannerDTO4.toEntity());
        bannerRepository.save(bannerDTO5.toEntity());
        bannerRepository.save(bannerDTO6.toEntity());
        bannerRepository.save(bannerDTO7.toEntity());
    }

    @Test
    public void findTest(){
        Optional<Banner> banner = bannerRepository.findById(1L);

        if(banner.isPresent()){
            log.info("bannerName: " + banner.get().getBannerName());
        }
    }

    @Test
    public void findAllTest(){
        List<Banner> banners = bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "bannerId"));

        banners.stream().map(Banner::getBannerName).forEach(log::info);
    }

    @Test
    public void updateTest(){
        bannerRepository.findAll().get(0).update("byeolbyeol.png");
    }


//    @Test
//    public void deleteTest(){
//        bannerRepository.deleteById(1L);
//    }


}
