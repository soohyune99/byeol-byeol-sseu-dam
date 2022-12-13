package com.app.byeolbyeolsseudam.service.join;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.mypoint.MypointDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.mypoint.Mypoint;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.mypoint.MypointRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.xerces.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class JoinServiceImpl implements JoinService {
    private final MemberRepository memberRepository;
    private final MypointRepository mypointRepository;

    @Override
    public void memberJoin(MemberDTO memberDTO){
        memberDTO.setMemberLoginType(MemberLoginType.일반);
        memberDTO.setMemberCategory(MemberCategory.일반회원);
        memberDTO.setMemberPoint(3000);
        memberDTO.setMemberProfileName("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        memberDTO.setMemberPassword(Base64.encode(memberDTO.getMemberPassword().getBytes()));

        Member member = memberDTO.toEntity();
        memberRepository.save(member);

        MypointDTO mypointDTO = new MypointDTO();
        mypointDTO.setMypointInout(3000);
        mypointDTO.setMypointContent("신규 가입 포인트");

        Mypoint mypoint = mypointDTO.toEntity();
        mypoint.changeMember(member);

        mypointRepository.save(mypoint);
    }

    @Override
    public void crewJoin(MemberDTO memberDTO){
        memberDTO.setMemberLoginType(MemberLoginType.일반);
        memberDTO.setMemberCategory(MemberCategory.기사회원);
        memberDTO.setMemberProfileName("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460__340.png");
        memberDTO.setMemberPassword(Base64.encode(memberDTO.getMemberPassword().getBytes()));

        Member member = memberDTO.toEntity();
        memberRepository.save(member);
    }

    @Override
    public boolean checkEmail(String memberEmail) {
        boolean checkEmail = memberRepository.existsByMemberEmail(memberEmail);
        return checkEmail;
    }
}