package com.app.byeolbyeolsseudam.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PageDTO {
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev, next;
    private int total;
    private Criteria criteria;

    public void createPageDTO(Criteria criteria, int total){
        createPageDTO(criteria, total, 10);
    }

    public void createPageDTO(Criteria criteria, int total, int pageCount){
        this.criteria = criteria;
        this.total = total;
        this.pageCount = pageCount;
        this.endPage = (int)(Math.ceil(criteria.getPage() / (double)pageCount)) * pageCount;
        this.startPage = endPage - pageCount + 1;
        this.realEnd = (int)(Math.ceil((double)total / criteria.getAmount()));
        if(realEnd < endPage){
            endPage = realEnd == 0 ? 1 : realEnd;
        }
        this.prev = startPage > 1;
        this.next = endPage < realEnd;
    }
}
