package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BannerDTO;
import com.app.byeolbyeolsseudam.repository.BannerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class BannerTest {
    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){

        for (int i = 0; i < 7; i++){
            BannerDTO bannerDTO = new BannerDTO();

            UUID uuid = UUID.randomUUID();
            String filName = uuid.toString() + "banner" + (i+1) + ".png";

            bannerDTO.setBannerUuid(uuid.toString());
            bannerDTO.setBannerPath("C://upload");
            bannerDTO.setBannerName(filName);
            bannerRepository.save(bannerDTO.toEntity());
        }
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
//        List<Banner> bannerList = jpaQueryFactory.selectFrom(QBanner.banner)
//                .orderBy(QBanner.banner.bannerId.desc())
//                .offset(0)
//                .limit(3)
//                .fetch();

        List<Banner> banners = bannerRepository.findAll(Sort.by(Sort.Direction.DESC, "bannerId"));
        banners.stream().map(Banner::getBannerName).forEach(log::info);
    }

    @Test
    public void updateTest(){
        UUID uuid = UUID.randomUUID();

        bannerRepository.findAll().get(0).update(uuid.toString() + "byeolbyeol.png", "C://upload", uuid.toString());
    }


//    @Test
//    public void deleteTest(){
//        bannerRepository.deleteAll();
//    }


}
