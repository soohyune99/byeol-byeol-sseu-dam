package com.app.byeolbyeolsseudam.repository.admin.badge;

import com.app.byeolbyeolsseudam.entity.badge.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminBadgeRepository extends JpaRepository<Badge, Long>, AdminBadgeCustomRepository {
}
