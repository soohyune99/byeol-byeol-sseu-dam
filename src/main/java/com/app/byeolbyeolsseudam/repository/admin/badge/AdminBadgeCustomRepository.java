package com.app.byeolbyeolsseudam.repository.admin.badge;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AdminBadgeCustomRepository {
    public List<BadgeDTO> searchBadge(String keyword);
    public List<BadgeDTO> searchBadgePaging(String keyword, Pageable pageable);
    public List<BadgeDTO> selectById(Long badgeId);
    public void update(BadgeDTO badgeDTO);
}
