package com.app.byeolbyeolsseudam.repository.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;

public interface MemberCustomRepository {
    public MemberDTO selectMember(Long memberId);
    public MemberDTO getloginMember(Long memberId);
}
