package com.app.byeolbyeolsseudam.repository.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.member.QMemberDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.member.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.member.QMember.member;


@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberDTO selectMember(Long memberId){
        return jpaQueryFactory.select(new QMemberDTO(member.memberId,
                member.memberLoginType, member.memberCategory, member.memberName,
                member.memberPassword, member.memberPhone, member.memberEmail,
                member.memberPoint, member.memberProfileName))
                .from(member)
                .where(member.memberId.eq(memberId))
                .fetchOne();
    }

//    @Override
//    public MemberDTO getloginMember(Long memberId){
//        return jpaQueryFactory.select(new QMemberDTO(member.memberId,
//                member.memberLoginType, member.memberCategory, member.memberName,
//                member.memberPhone, member.memberEmail,
//                member.memberPoint, member.memberProfileName))
//                .from(member)
//                .where(member.memberId.eq(memberId))
//                .fetchOne();
//    }

    @Override
    public Member findOauth(MemberDTO memberDTO){
        Member member = jpaQueryFactory.selectFrom(QMember.member)
                            .where(QMember.member.memberLoginType.eq(memberDTO.getMemberLoginType())
                            .and(QMember.member.memberEmail.eq(memberDTO.getMemberEmail())))
                            .fetchOne();
        if(Optional.ofNullable(member).isPresent()){
            return Optional.of(member).get();
        }else {
            return null;
        }
    }
}
