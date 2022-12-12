package com.app.byeolbyeolsseudam.service.login;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService{
    private final MemberRepository memberRepository;
    JavaMailSender mailSender;

    @Override
    public Member login(MemberDTO memberDTO){
        return memberRepository.findByMemberEmailAndMemberPassword(memberDTO.getMemberEmail(), memberDTO.getMemberPassword());
    }

    @Override
    public MemberDTO getMemberDTO(Long memberId){
        return memberRepository.selectMember(memberId);
    }
}
