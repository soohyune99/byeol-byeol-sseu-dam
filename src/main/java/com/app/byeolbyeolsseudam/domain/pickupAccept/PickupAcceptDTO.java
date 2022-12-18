package com.app.byeolbyeolsseudam.domain.pickupAccept;

import com.app.byeolbyeolsseudam.domain.member.MemberDTO;
import com.app.byeolbyeolsseudam.domain.pickup.PickupDTO;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.pickup.Pickup;
import com.app.byeolbyeolsseudam.type.PickupStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class PickupAcceptDTO {
    private Long pickupAcceptId;
    private Long pickupId;
    private int petCount;
    private int glassCount;
    private String pickupAddress;
    private String pickupMessage;
    private PickupStatus pickupStatus; //수거상태
    private Long memberId; // 기사님 아이디
    private String memberName; // 기사님 이름
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdDate; // 수거 수락일

    private Long pickupMemberId;  // 신청자 아이디
    private String pickupMemberName; // 신청자 이름
//    private String pickupMemberProfilePath; //신청자 프로필사진
    private String pickupMemberProfileName; //신청자 프로필사진
    private String pickupMemberPhone; // 신청자 핸드폰번호
    private LocalDateTime updateDate; // 수거 완료일

    @QueryProjection
    public PickupAcceptDTO(Long pickupAcceptId, Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, LocalDateTime createdDate) {
        this.pickupAcceptId = pickupAcceptId;
        this.pickupId = pickupId;
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.memberId = memberId;
        this.memberName = memberName;
        this.createdDate = createdDate;
    }

//    @QueryProjection
//    public PickupAcceptDTO(Long pickupAcceptId, Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, LocalDateTime createdDate, LocalDateTime updateDate, Long pickupMemberId, String pickupMemberName, String pickupMemberProfilePath, String pickupMemberPhone) {
//        this.pickupAcceptId = pickupAcceptId;
//        this.pickupId = pickupId;
//        this.petCount = petCount;
//        this.glassCount = glassCount;
//        this.pickupAddress = pickupAddress;
//        this.pickupMessage = pickupMessage;
//        this.pickupStatus = pickupStatus;
//        this.memberId = memberId;
//        this.memberName = memberName;
//        this.createdDate = createdDate;
//        this.updateDate = updateDate;
//        this.pickupMemberId = pickupMemberId;
//        this.pickupMemberName = pickupMemberName;
//        this.pickupMemberProfilePath = pickupMemberProfilePath;
//        this.pickupMemberPhone = pickupMemberPhone;
//    }
    @QueryProjection
    public PickupAcceptDTO(Long pickupAcceptId, Long pickupId, int petCount, int glassCount, String pickupAddress, String pickupMessage, PickupStatus pickupStatus, Long memberId, String memberName, LocalDateTime createdDate, LocalDateTime updateDate, Long pickupMemberId, String pickupMemberName, String pickupMemberProfileName, String pickupMemberPhone) {
        this.pickupAcceptId = pickupAcceptId;
        this.pickupId = pickupId;
        this.petCount = petCount;
        this.glassCount = glassCount;
        this.pickupAddress = pickupAddress;
        this.pickupMessage = pickupMessage;
        this.pickupStatus = pickupStatus;
        this.memberId = memberId;
        this.memberName = memberName;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
        this.pickupMemberId = pickupMemberId;
        this.pickupMemberName = pickupMemberName;
        this.pickupMemberProfileName = pickupMemberProfileName;
        this.pickupMemberPhone = pickupMemberPhone;
    }

}
