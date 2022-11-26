package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Notice;
import com.app.byeolbyeolsseudam.type.NoticeCategory;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class NoticeDTO {
    private Long noticeId;
    private String noticeTitle;
    private String noticeContent;
    private NoticeCategory noticeCategory;
    private LocalDateTime createdDate;

    @QueryProjection
    public NoticeDTO(Long noticeId, String noticeTitle, String noticeContent, NoticeCategory noticeCategory, LocalDateTime createdDate) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.noticeCategory = noticeCategory;
        this.createdDate = createdDate;
    }

    public Notice toEntity(){
        return Notice.builder()
                .noticeTitle(noticeTitle)
                .noticeContent(noticeContent)
                .noticeCategory(noticeCategory)
                .build();
    }
}
