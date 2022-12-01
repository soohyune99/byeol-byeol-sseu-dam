package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.ProgramDTO;
import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import com.sun.istack.NotNull;
import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_PROGRAM")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program extends Period {
    @Id @GeneratedValue @NotNull
    private Long programId;
    @NotNull
    private String programName;
    @NotNull
    private String programPlace;
    @Embedded @NotNull
    private PossibleDate possibleDate;
    @NotNull
    private int programTime;
    @NotNull
    private LocalDateTime programDate;
    @NotNull
    private String programContent;
    @NotNull
    private int programLimitCount;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus;
    @NotNull
    private String programFileProfileName;
    @NotNull
    private String programFileProfilePath;
    @NotNull
    private String programFileProfileUuid;
    @NotNull
    private String programFileDetailName;
    @NotNull
    private String programFileDetailPath;
    @NotNull
    private String programFileDetailUuid;

    @Builder
    public Program(String programName, String programPlace, PossibleDate possibleDate, int programTime, LocalDateTime programDate, String programContent, int programLimitCount, ProgramStatus programStatus, String programFileProfileName, String programFileProfilePath, String programFileProfileUuid, String programFileDetailName, String programFileDetailPath, String programFileDetailUuid) {
        this.programName = programName;
        this.programPlace = programPlace;
        this.possibleDate = possibleDate;
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
    }

    public void update(ProgramDTO programDTO) {
        this.programName = programDTO.getProgramName();
        this.programPlace = programDTO.getProgramPlace();
        this.programTime = programDTO.getProgramTime();
        this.programDate = programDTO.getProgramDate();
        this.programContent = programDTO.getProgramContent();
        this.programLimitCount = programDTO.getProgramLimitCount();
        this.programStatus = programDTO.getProgramStatus();
        this.programFileProfileName = programDTO.getProgramFileProfileName();
        this.programFileProfilePath = programDTO.getProgramFileProfilePath();
        this.programFileProfileUuid = programDTO.getProgramFileProfileUuid();
        this.programFileDetailName = programDTO.getProgramFileDetailName();
        this.programFileDetailPath = programDTO.getProgramFileDetailPath();
        this.programFileDetailUuid = programDTO.getProgramFileDetailUuid();
        this.possibleDate.setOpeningDate(programDTO.getOpeningDate());
        this.possibleDate.setClosingDate(programDTO.getClosingDate());
    }
}
