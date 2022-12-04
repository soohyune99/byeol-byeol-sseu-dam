package com.app.byeolbyeolsseudam.service.admin;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.app.byeolbyeolsseudam.entity.member.QMember;
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
    private final JPAQueryFactory jpaQueryFactory;
    private final MemberRepository memberRepository;

    public List<MemberDTO> showMemberList(){
        return jpaQueryFactory.select(new QMemberDTO(
                member.memberId,
                member.memberLoginType,
                member.memberCategory,
                member.memberName,
                member.memberPassword,
                member.memberPhone,
                member.memberEmail,
                member.memberPoint,
                member.memberProfileName,
                member.memberProfilePath,
                member.memberProfileUuid,
                member.createdDate
        )).from(member)
                .orderBy(member.memberId.desc())
                .limit(10)
                .fetch();

    };

}
