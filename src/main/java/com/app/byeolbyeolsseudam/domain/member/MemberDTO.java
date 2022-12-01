package com.app.byeolbyeolsseudam.domain.member;

import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class MemberDTO {
    private Long memberId;
    private MemberLoginType memberLoginType;
    private MemberCategory memberCategory;
    private String memberName;
    private String memberPassword;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private int memberPoint;
    private String memberProfileName;
    private String memberProfilePath;
    private String memberProfileUuid;

    @QueryProjection
    public MemberDTO(Long memberId, MemberLoginType memberLoginType, MemberCategory memberCategory, String memberName, String memberPassword, String memberPhone, String memberEmail, String memberAddress, int memberPoint, String memberProfileName, String memberProfilePath, String memberProfileUuid) {
        this.memberId = memberId;
        this.memberLoginType = memberLoginType;
        this.memberCategory = memberCategory;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberPoint = memberPoint;
        this.memberProfileName = memberProfileName;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUuid = memberProfileUuid;
    }

    public Member toEntity(){
        return Member.builder()
                .memberLoginType(memberLoginType)
                .memberCategory(memberCategory)
                .memberName(memberName)
                .memberPassword(memberPassword)
                .memberPhone(memberPhone)
                .memberEmail(memberEmail)
                .memberPoint(memberPoint)
                .memberProfileName(memberProfileName)
                .memberProfilePath(memberProfilePath)
                .memberProfileUuid(memberProfileUuid)
                .build();
    }
}
