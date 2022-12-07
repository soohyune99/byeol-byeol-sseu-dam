package com.app.byeolbyeolsseudam.repository.banner;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BannerCustomRepository{
    public List<BannerDTO> showList();

    public BannerDTO selectById(Long bannerId);

    public void update(BannerDTO bannerDTO);
}
