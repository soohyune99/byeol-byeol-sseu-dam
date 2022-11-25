package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
