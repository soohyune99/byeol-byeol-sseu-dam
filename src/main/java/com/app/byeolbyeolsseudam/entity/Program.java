package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.embaddable.PossibleDate;
import com.app.byeolbyeolsseudam.type.ProgramStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_PROGRAM")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Program extends Period {
    @Id @GeneratedValue
    private Long programId;
    private String programName;
    private String programPlace;
    @Embedded
    private PossibleDate possibleDate;
    private LocalDateTime programTime;
    private String programDetail;
    private int programLimitCount;
    @Enumerated(EnumType.STRING)
    private ProgramStatus programStatus;
    private String programFile;
}
