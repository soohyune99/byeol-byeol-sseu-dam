package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.entity.Program;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class ProgramDTO {
    private Long programId;
    private String programName;
    private String programPlace;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private int programTime;
    private LocalDateTime programDate;
    private String programDetail;
    private int programLimitCount;
    private ProgramStatus programStatus;
    private String programFile;
    private LocalDateTime createdDate;

    @QueryProjection
    public ProgramDTO(Long programId, String programName, String programPlace, LocalDateTime openingDate, LocalDateTime closingDate, int programTime, LocalDateTime programDate, String programDetail, int programLimitCount, ProgramStatus programStatus, String programFile, LocalDateTime createdDate) {
        this.programId = programId;
        this.programName = programName;
        this.programPlace = programPlace;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.programTime = programTime;
        this.programDate = programDate;
        this.programDetail = programDetail;
        this.programLimitCount = programLimitCount;
        this.programStatus = programStatus;
        this.programFile = programFile;
        this.createdDate = createdDate;
    }

    public Program toEntity(){
        return Program.builder()
                .programName(programName)
                .programPlace(programPlace)
                .openingDate(openingDate)
                .closingDate(closingDate)
                .programTime(programTime)
                .programDate(programDate)
                .programDetail(programDetail)
                .programLimitCount(programLimitCount)
                .programStatus(ProgramStatus.모집예정)
                .programFile(programFile)
                .build();
    }
}
