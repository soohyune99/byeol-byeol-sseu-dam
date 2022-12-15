package com.app.byeolbyeolsseudam.service.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public Member login(MemberDTO memberDTO);
    public MemberDTO getMemberDTO(Long memberId);
    public Boolean send(MemberDTO memberDTO);
    public Member loginOauth(MemberDTO memberDTO);
}
