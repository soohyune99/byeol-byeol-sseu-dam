package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.badge.BadgeDTO;
import com.app.byeolbyeolsseudam.entity.badge.Badge;
import com.app.byeolbyeolsseudam.repository.admin.badge.AdminBadgeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminBadgeService {
    private final AdminBadgeRepository adminBadgeRepository;

    public Page<BadgeDTO> searchBadge(Pageable pageable){
        List<BadgeDTO> badges = adminBadgeRepository.searchBadgePaging("",pageable);

        final Page<BadgeDTO> badgeList = new PageImpl<>(badges,pageable,adminBadgeRepository.findAll().size());
        return badgeList;
    }
    public Page<BadgeDTO> searchBadgePaging(String keyword, Pageable pageable){
        List<BadgeDTO> search = adminBadgeRepository.searchBadge(keyword);
        List<BadgeDTO> badges = adminBadgeRepository.searchBadgePaging(keyword, pageable);

        final Page<BadgeDTO> badgeSearchList = new PageImpl<>(badges,pageable,search.size());
        return badgeSearchList;

    }

    public BadgeDTO selectById(String badgeIdstr){
        Long badgeId = Long.parseLong(badgeIdstr);
        return adminBadgeRepository.selectById(badgeId);
    }

    public void updateBadge(BadgeDTO badgeDTO, Long badgeId){
        adminBadgeRepository.update(badgeDTO);
        Badge badge = adminBadgeRepository.findById(badgeId).get();
        adminBadgeRepository.save(badge);
    }

    public void removeBadge(List<String> badgeIdstr){
        List<Long> badgeIds = new ArrayList<>();
        badgeIdstr.stream().map(Long::parseLong).forEach(badgeIds::add);
        badgeIds.forEach(adminBadgeRepository::deleteById);

    }
}
