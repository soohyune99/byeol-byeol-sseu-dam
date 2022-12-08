package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MypageService {
    public List<MybadgeDTO> selectMybadges();
    public List<BadgeDTO> showBadgeList();

    public List<MyprogramDTO> showMyprogramList(Long memberId);
    public List<MypointDTO> showMypointList(Long memberId);
}
