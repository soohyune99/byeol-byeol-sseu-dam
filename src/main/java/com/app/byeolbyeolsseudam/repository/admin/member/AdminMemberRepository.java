package com.app.byeolbyeolsseudam.repository.admin.member;

import com.app.byeolbyeolsseudam.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminMemberRepository extends JpaRepository<Member, Long>, AdminMemberCustomRepository {

}
