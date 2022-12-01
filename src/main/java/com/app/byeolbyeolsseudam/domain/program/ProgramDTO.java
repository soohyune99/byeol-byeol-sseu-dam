package com.app.byeolbyeolsseudam.domain.program;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.entity.program.Program;
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
    private String programContent;
    private int programLimitCount;
    private ProgramStatus programStatus;
    private String programFileProfileName;
    private String programFileProfilePath;
    private String programFileProfileUuid;
    private String programFileDetailName;
    private String programFileDetailPath;
    private String programFileDetailUuid;
    private LocalDateTime createdDate;

    @QueryProjection
    public ProgramDTO(Long programId, String programName, String programPlace, LocalDateTime openingDate, LocalDateTime closingDate, int programTime, LocalDateTime programDate, String programContent, int programLimitCount, ProgramStatus programStatus, String programFileProfileName, String programFileProfilePath, String programFileProfileUuid, String programFileDetailName, String programFileDetailPath, String programFileDetailUuid, LocalDateTime createdDate) {
        this.programId = programId;
        this.programName = programName;
        this.programPlace = programPlace;
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.programTime = programTime;
        this.programDate = programDate;
        this.programContent = programContent;
        this.programLimitCount = programLimitCount;
        this.programStatus = programStatus;
        this.programFileProfileName = programFileProfileName;
        this.programFileProfilePath = programFileProfilePath;
        this.programFileProfileUuid = programFileProfileUuid;
        this.programFileDetailName = programFileDetailName;
        this.programFileDetailPath = programFileDetailPath;
        this.programFileDetailUuid = programFileDetailUuid;
        this.createdDate = createdDate;
    }

    public Program toEntity(){
        PossibleDate possibleDate = new PossibleDate();
        possibleDate.setOpeningDate(openingDate);
        possibleDate.setClosingDate(closingDate);

        return Program.builder()
                .programName(programName)
                .programPlace(programPlace)
                .possibleDate(possibleDate)
                .programTime(programTime)
                .programDate(programDate)
                .programContent(programContent)
                .programLimitCount(programLimitCount)
                .programStatus(ProgramStatus.모집예정)
                .programFileProfileName(programFileProfileName)
                .programFileDetailPath(programFileProfilePath)
                .programFileDetailUuid(programFileProfileUuid)
                .programFileDetailName(programFileDetailName)
                .programFileDetailPath(programFileDetailPath)
                .programFileDetailUuid(programFileDetailUuid)
                .build();
    }
}
