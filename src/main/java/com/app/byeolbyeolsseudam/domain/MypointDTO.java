package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Mypoint;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Component
@Data
@NoArgsConstructor
public class MypointDTO {
    private Long mypointId;
    private String mypointContent;
    private int mypointInout;
    private Member member;

    @QueryProjection
    public MypointDTO(Long mypointId, String mypointContent, int mypointInout, Member member) {
        this.mypointId = mypointId;
        this.mypointContent = mypointContent;
        this.mypointInout = mypointInout;
        this.member = member;
    }

    public Mypoint toEntity(){
        return Mypoint.builder()
                .mypointContent(mypointContent)
                .mypointInout(mypointInout)
                .member(member)
                .build();
    }
}
