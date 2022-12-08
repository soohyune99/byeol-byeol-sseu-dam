package com.app.byeolbyeolsseudam.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Criteria {
    private int page;
    private String keyword;
    private String category;
}