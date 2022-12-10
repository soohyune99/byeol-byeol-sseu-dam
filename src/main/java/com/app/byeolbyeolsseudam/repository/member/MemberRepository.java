package com.app.byeolbyeolsseudam.repository.member;

import com.app.byeolbyeolsseudam.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberCustomRepository{

    //    public List<Member> findByMemberEmail(@Param("memberEmail") String memberEmail);

    public Member findByMemberEmail(@Param("memberEmail") String memberEmail);

    public boolean existsByMemberEmail(@Param("memberEmail") String memberEmail);
}
