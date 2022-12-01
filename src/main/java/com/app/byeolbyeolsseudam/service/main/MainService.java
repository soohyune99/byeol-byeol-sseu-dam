package com.app.byeolbyeolsseudam.service.main;

import com.app.byeolbyeolsseudam.domain.banner.BannerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MainService {
    //메인 배너 리스트
    public List<BannerDTO> showList();
}
