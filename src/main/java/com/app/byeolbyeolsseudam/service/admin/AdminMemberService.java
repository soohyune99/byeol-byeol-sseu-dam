package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.app.byeolbyeolsseudam.entity.member.QMember;
import com.app.byeolbyeolsseudam.repository.admin.member.AdminMemberRepository;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Service
@RequiredArgsConstructor
public class AdminMemberService {
    private final AdminMemberRepository adminMemberRepository;
    public List<MemberDTO> showMemberList(){
        return adminMemberRepository.showMemberList();
    };
}
