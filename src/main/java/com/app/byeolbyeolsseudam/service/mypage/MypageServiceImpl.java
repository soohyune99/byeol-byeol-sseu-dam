package com.app.byeolbyeolsseudam.service.mypage;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.domain.myprogram.MyprogramDTO;
import com.app.byeolbyeolsseudam.repository.badge.BadgeRepository;
import com.app.byeolbyeolsseudam.repository.mybadge.MybadgeRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.repository.myprogram.MyprogramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{
    private final MypointRepository mypointRepository;
    private final BadgeRepository badgeRepository;
    private final MybadgeRepository mybadgeRepository;
    private final MyprogramRepository myprogramRepository;

    @Override
    public List<MybadgeDTO> selectMybadges(){
        return mybadgeRepository.selectMybadges();
    }

    @Override
    public List<BadgeDTO> showBadgeList(){
        return badgeRepository.showBadgeList();
    }

    @Override
    public List<MyprogramDTO> showMyprogramList(Long memberId){
        return myprogramRepository.showMyprogramList(memberId);
    }

    @Override
    public List<MypointDTO> showMypointList(Long memberId){
        return mypointRepository.showMypointList(memberId);
    }
}
