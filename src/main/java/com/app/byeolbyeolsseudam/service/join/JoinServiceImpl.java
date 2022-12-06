package com.app.byeolbyeolsseudam.service.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {

    private final MemberRepository memberRepository;

    @Override
    public void memberJoin(MemberDTO memberDTO){
        memberDTO.setMemberLoginType(MemberLoginType.일반);
        memberDTO.setMemberCategory(MemberCategory.일반회원);
        memberDTO.setMemberName(memberDTO.getMemberName());
        memberDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberDTO.setMemberPassword(memberDTO.getMemberPassword());
        memberRepository.save(memberDTO.toEntity());
    }

    @Override
    public void crewJoin(MemberDTO memberDTO){
        memberDTO.setMemberLoginType(MemberLoginType.일반);
        memberDTO.setMemberCategory(MemberCategory.기사회원);
        memberDTO.setMemberName(memberDTO.getMemberName());
        memberDTO.setMemberEmail(memberDTO.getMemberEmail());
        memberDTO.setMemberPassword(memberDTO.getMemberPassword());
        memberDTO.setMemberPhone(memberDTO.getMemberPhone());
        memberRepository.save(memberDTO.toEntity());

    }

//    @Override
//    public List<MemberDTO> findAll(){
//        .findAll();
//    }

//    @Override
//    public Member find(Long memberId){
//        return memberRepository.findById(memberId).get();
//
//    }
//
//    @Override
//    public MemberDTO findByEmail(String memberEmail){
//        return memberRepository.findByMemberEmail(memberEmail);
//    }
//
//    @Override
//    public void update(MemberDTO memberDTO){
//
//    }


}