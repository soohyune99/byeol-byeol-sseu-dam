package com.app.byeolbyeolsseudam.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Payment {
    private String zipcode;
    private String roadAdd;
    private String detailAdd;
    private String writeAdd;
    private String message;

    private int buyCount;

    private Long memberId;
    private Long productId;
    private String basketId;
}
