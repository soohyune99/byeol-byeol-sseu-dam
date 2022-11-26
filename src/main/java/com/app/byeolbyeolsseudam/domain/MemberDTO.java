package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

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
    private String memberProfileFile;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    @QueryProjection
    public MemberDTO(Long memberId, MemberLoginType memberLoginType, MemberCategory memberCategory, String memberName, String memberPassword, String memberPhone, String memberEmail, String memberAddress, int memberPoint, String memberProfileFile, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.memberId = memberId;
        this.memberLoginType = memberLoginType;
        this.memberCategory = memberCategory;
        this.memberName = memberName;
        this.memberPassword = memberPassword;
        this.memberPhone = memberPhone;
        this.memberEmail = memberEmail;
        this.memberAddress = memberAddress;
        this.memberPoint = memberPoint;
        this.memberProfileFile = memberProfileFile;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Member toEntity(){
        return Member.builder()
                .memberLoginType(memberLoginType)
                .memberCategory(memberCategory)
                .memberName(memberName)
                .memberPassword(memberPassword)
                .memberPhone(memberPhone)
                .memberEmail(memberEmail)
                .memberAddress(memberAddress)
                .memberPoint(memberPoint)
                .memberProfileFile(memberProfileFile)
                .build();
    }
}
