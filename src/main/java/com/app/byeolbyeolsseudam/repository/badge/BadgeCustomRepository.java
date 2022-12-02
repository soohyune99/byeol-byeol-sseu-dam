package com.app.byeolbyeolsseudam.repository.badge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;

import java.util.List;

public interface BadgeCustomRepository {
    public List<BadgeDTO> showBadgeList();
}
