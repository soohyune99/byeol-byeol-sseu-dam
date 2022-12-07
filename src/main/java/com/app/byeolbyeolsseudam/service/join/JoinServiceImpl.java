package com.app.byeolbyeolsseudam.service.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
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
        memberDTO.setMemberAddress(memberDTO.getMemberAddress());
        memberRepository.save(memberDTO.toEntity());

    }

    @Override
    public boolean checkEmail(String memberEmail) {
        boolean checkEmail = memberRepository.existsByMemberEmail(memberEmail);
        if (checkEmail) {
            log.info("트루값이다");
            return true;
        } else {
            log.info("폴스값이다");
            return false;
        }
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