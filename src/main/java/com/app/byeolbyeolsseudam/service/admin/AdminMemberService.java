package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.app.byeolbyeolsseudam.entity.member.QMember;
import com.app.byeolbyeolsseudam.repository.admin.member.AdminMemberRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    private final AdminMemberRepository adminMemberRepository;
    public Page<MemberDTO> showMemberList(Pageable pageable){
        List<MemberDTO> members = adminMemberRepository.showMemberList(pageable);
        final Page<MemberDTO> memberPage = new PageImpl<>(members,pageable,adminMemberRepository.findAll().size());

        return memberPage;
    };

    public MemberDTO showAdmin(){
        return adminMemberRepository.showAdminList();
    }

    public void removeMember(List<String> memberIdstr){
        List<Long> memberId = new ArrayList<>();
        memberIdstr.stream().map(Long::parseLong).forEach(memberId::add);
        memberId.forEach(adminMemberRepository::deleteById);
    }
}
