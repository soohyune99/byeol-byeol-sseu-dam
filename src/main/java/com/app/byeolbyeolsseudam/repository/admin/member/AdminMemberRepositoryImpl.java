package com.app.byeolbyeolsseudam.repository.admin.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class AdminMemberRepositoryImpl implements AdminMemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberDTO> showMemberList() {
        return jpaQueryFactory.select(new QMemberDTO(
                member.memberId,
                member.memberLoginType,
                member.memberCategory,
                member.memberName,
                member.memberPassword,
                member.memberPhone,
                member.memberAddress,
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
    }
}
