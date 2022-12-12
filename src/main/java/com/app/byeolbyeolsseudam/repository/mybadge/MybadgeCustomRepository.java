package com.app.byeolbyeolsseudam.repository.mybadge;

import com.app.byeolbyeolsseudam.domain.mybadge.MybadgeDTO;
import com.app.byeolbyeolsseudam.entity.mybadge.Mybadge;

import java.util.List;

public interface MybadgeCustomRepository {
    public List<MybadgeDTO> selectMybadges(Long memberId);
}
