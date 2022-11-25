package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.MemberCategory;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MEMBER")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends Period {
    @Id @GeneratedValue
    private Long memberId;
    @Enumerated(EnumType.STRING)
    private MemberCategory memberCategory;
    private String memberName;
    private String memberPassword;
    private String memberPhone;
    private String memberEmail;
    private String memberAddress;
    private int memberPoint;
    private String memberProfileFile;
}
