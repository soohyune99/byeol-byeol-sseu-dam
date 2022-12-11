package com.app.byeolbyeolsseudam.entity.member;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Period {
    @Id @GeneratedValue @NotNull
    private Long memberId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberLoginType memberLoginType;
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberCategory memberCategory;
    @NotNull
    private String memberName;
    @NotNull
    private String memberPassword;
    //    @NotNull
    private String memberPhone;
    private String memberAddress;
    @NotNull
    private String memberEmail;
    private int memberPoint;
    private String memberProfileName;
    private String memberProfilePath;
    private String memberProfileUuid;

    @Builder
    public Member(MemberLoginType memberLoginType, MemberCategory memberCategory, String memberName, String memberPassword, String memberPhone, String memberAddress, String memberEmail, int memberPoint, String memberProfileName, String memberProfilePath, String memberProfileUuid) {
        this.memberLoginType = memberLoginType;
        this.memberCategory = memberCategory;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberAddress = memberAddress;
        this.memberEmail = memberEmail;
        this.memberPoint = memberPoint;
        this.memberProfileName = memberProfileName;
        this.memberProfilePath = memberProfilePath;
        this.memberProfileUuid = memberProfileUuid;
    }

    public void update(MemberDTO memberDTO){
        this.memberName = memberDTO.getMemberName();
        this.memberPassword = memberDTO.getMemberPassword();
        this.memberPhone = memberDTO.getMemberPhone();
        this.memberAddress = memberDTO.getMemberAddress();
        this.memberProfileName = memberDTO.getMemberProfileName();
    }

    public void updateMemberCategory(MemberCategory memberCategory){
        this.memberCategory = memberCategory;
    }

    public void updateMemberCategory(MemberDTO memberDTO){
        this.memberCategory = memberDTO.getMemberCategory();
    }

    public void updateMemberPhone(MemberDTO memberDTO){
        this.memberPoint = memberDTO.getMemberPoint();
    }

    public void updateMemberPassword(MemberDTO memberDTO){
        this.memberPassword = memberDTO.getMemberPassword();
    }
}
