package com.app.byeolbyeolsseudam.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Search {
    private String keyword;
    private String programStatus;
}