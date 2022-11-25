package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.NoticeCategory;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;
}
