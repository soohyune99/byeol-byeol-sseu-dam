package com.app.byeolbyeolsseudam.domain.myprogram;

import com.app.byeolbyeolsseudam.entity.myprogram.Myprogram;
import com.app.byeolbyeolsseudam.type.MyprogramStatus;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MyprogramDTO {
    private Long myprogramId;
    private MyprogramStatus myprogramStatus;
    private Long memberId;
    private String memberName;
    private Long programId;
    private String programName;
    private String programPlace;
    private LocalDateTime programDate;
    private String programFileProfileName;

    @QueryProjection
    public MyprogramDTO(Long myprogramId, MyprogramStatus myprogramStatus, Long memberId, Long programId, String programName, String programPlace, LocalDateTime programDate, String programFileProfileName) {
        this.myprogramId = myprogramId;
        this.myprogramStatus = myprogramStatus;
        this.memberId = memberId;
        this.programId = programId;
        this.programName = programName;
        this.programPlace = programPlace;
        this.programDate = programDate;
        this.programFileProfileName = programFileProfileName;
    }

    @QueryProjection
    public MyprogramDTO(Long myprogramId, MyprogramStatus myprogramStatus, Long memberId,String memberName, Long programId, String programName, String programPlace, LocalDateTime programDate, String programFileProfileName) {
        this.myprogramId = myprogramId;
        this.myprogramStatus = myprogramStatus;
        this.memberId = memberId;
        this.memberName = memberName;
        this.programId = programId;
        this.programName = programName;
        this.programPlace = programPlace;
        this.programDate = programDate;
        this.programFileProfileName = programFileProfileName;
    }

    public Myprogram toEntity(){
        return Myprogram.builder()
                .myprogramStatus(myprogramStatus)
                .build();
    }
}
