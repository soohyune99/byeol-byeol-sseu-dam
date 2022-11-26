package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Myprogram;
import com.app.byeolbyeolsseudam.entity.Program;
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
    private Member member;
    private Program program;
    private LocalDateTime createdDate;

    @QueryProjection
    public MyprogramDTO(Long myprogramId, Member member, Program program, LocalDateTime createdDate) {
        this.myprogramId = myprogramId;
        this.member = member;
        this.program = program;
        this.createdDate = createdDate;
    }

    public Myprogram toEntity(){
        return Myprogram.builder()
                .member(member)
                .program(program)
                .build();
    }
}
