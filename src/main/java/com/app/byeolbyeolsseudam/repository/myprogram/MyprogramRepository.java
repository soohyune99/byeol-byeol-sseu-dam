package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Badge;
import com.app.byeolbyeolsseudam.entity.Myprogram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyprogramRepository extends JpaRepository<Myprogram, Long> {
}
