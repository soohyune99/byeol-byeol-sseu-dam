package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Banner;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BannerDTO {
    private Long bannerId;
    private String bannerName;

    @QueryProjection
    public BannerDTO(Long bannerId, String bannerName) {
        this.bannerId = bannerId;
        this.bannerName = bannerName;
    }

    public Banner toEntity(){
        return Banner.builder()
                .bannerName(bannerName)
                .build();
    }
}
