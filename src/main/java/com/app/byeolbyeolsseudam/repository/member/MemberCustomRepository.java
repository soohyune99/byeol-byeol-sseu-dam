package com.app.byeolbyeolsseudam.repository.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;

public interface MemberCustomRepository {
    public MemberDTO selectMember(Long memberId);
}
