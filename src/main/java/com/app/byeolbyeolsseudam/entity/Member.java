package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter @Setter
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
    @NotNull
    private String memberPhone;
    @NotNull
    private String memberEmail;
    @NotNull
    private String memberAddress;
    private int memberPoint;
    private String memberProfileFile;

    @Builder
    public Member(MemberLoginType memberLoginType, MemberCategory memberCategory, String memberName, String memberPassword, String memberPhone, String memberEmail, String memberAddress, int memberPoint, String memberProfileFile) {
        this.memberLoginType = memberLoginType;
        this.memberCategory = memberCategory;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberPoint = memberPoint;
        this.memberProfileFile = memberProfileFile;
    }

    public void update(MemberCategory memberCategory, String memberName, String memberPassword, String memberPhone, String memberEmail, String memberAddress, int memberPoint, String memberProfileFile){
        this.memberCategory = memberCategory;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberPoint = memberPoint;
        this.memberProfileFile = memberProfileFile;
    }
}
