package com.app.byeolbyeolsseudam.service.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import org.springframework.stereotype.Service;

@Service
public interface JoinService {
    // 회원 가입
    public void memberJoin(MemberDTO memberDTO);
    // 기사 가입
    public void crewJoin(MemberDTO memberDTO);
    // 기사 전환
    public void changeCrew(MemberDTO memberDTO);
    // 이메일 중복검사
    public  boolean checkEmail(String memberEmail);

}

