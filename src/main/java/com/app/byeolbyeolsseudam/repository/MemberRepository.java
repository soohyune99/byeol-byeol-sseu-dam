package com.app.byeolbyeolsseudam.repository;

import com.app.byeolbyeolsseudam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public List<Member> findByMemberEmail(@Param("memberEmail") String memberEmail);
}
