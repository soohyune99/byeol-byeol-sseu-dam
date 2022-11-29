package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Myprogram;
import com.app.byeolbyeolsseudam.entity.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class MyprogramDTO {
    private Long myprogramId;
    private Long memberId;
    private Long programId;
    private String programName;
    private ProgramStatus programStatus;
    private String programFile;
    private String programFileProfileName;
    private String programFileProfilePath;
    private String programFileProfileUuid;
    private LocalDateTime createdDate;

    @QueryProjection
    public MyprogramDTO(Long myprogramId, Long memberId, Long programId, String programName, ProgramStatus programStatus, String programFile, String programFileProfileName, String programFileProfilePath, String programFileProfileUuid, LocalDateTime createdDate) {
        this.myprogramId = myprogramId;
        this.memberId = memberId;
        this.programId = programId;
        this.programName = programName;
        this.programStatus = programStatus;
        this.programFile = programFile;
        this.programFileProfileName = programFileProfileName;
        this.programFileProfilePath = programFileProfilePath;
        this.programFileProfileUuid = programFileProfileUuid;
        this.createdDate = createdDate;
    }

    public Myprogram toEntity(Member member, Program program){
        return Myprogram.builder()
                .member(member)
                .program(program)
                .build();
    }
}
