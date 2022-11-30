package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.NoticeDTO;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_NOTICE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice extends Period {
    @Id @GeneratedValue @NotNull
    private Long noticeId;
    @NotNull
    private String noticeTitle;
    @NotNull
    private String noticeContent;
    @NotNull
    @Enumerated(EnumType.STRING)
    private NoticeCategory noticeCategory;

    @Builder
    public Notice(String noticeTitle, String noticeContent, NoticeCategory noticeCategory) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCategory = noticeCategory;
    }

    public void update(NoticeDTO noticeDTO){
        this.noticeTitle = noticeDTO.getNoticeTitle();
        this.noticeContent = noticeDTO.getNoticeContent();
        this.noticeCategory = noticeDTO.getNoticeCategory();
    }
}
