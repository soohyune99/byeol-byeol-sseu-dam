package com.app.byeolbyeolsseudam.repository.admin.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;

@Repository
@RequiredArgsConstructor
public class AdminMemberRepositoryImpl implements AdminMemberCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberDTO> showMemberList(Pageable pageable) {
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
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public MemberDTO showAdminList() {
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
                .where(member.memberCategory.eq(MemberCategory.관리자))
                .orderBy(member.memberId.desc())
                .fetchOne();
    }
}
