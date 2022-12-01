package com.app.byeolbyeolsseudam.repository.member;

import com.app.byeolbyeolsseudam.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public List<Member> findByMemberEmail(@Param("memberEmail") String memberEmail);
}
