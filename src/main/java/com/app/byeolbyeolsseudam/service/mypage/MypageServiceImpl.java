package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{
    private final MypointRepository mypointRepository;
    private final BadgeRepository badgeRepository;
    private final MybadgeRepository mybadgeRepository;

    @Override
    public List<MypointDTO> selectPoints(){
        return mypointRepository.selectPoints();
    }

    @Override
    public List<MybadgeDTO> selectMybadges(){
        return mybadgeRepository.selectMybadges();
    }

    @Override
    public List<BadgeDTO> showBadgeList(){
        return badgeRepository.showBadgeList();
    }
}
