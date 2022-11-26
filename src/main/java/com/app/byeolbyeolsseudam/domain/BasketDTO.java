package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Basket;
import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class BasketDTO {
    private Long basketId;
    private Member member;
    private Product product;

    @QueryProjection
    public BasketDTO(Long basketId, Member member, Product product) {
        this.basketId = basketId;
        this.member = member;
        this.product = product;
    }

    public Basket toEntity(){
        return Basket.builder()
                .member(member)
                .product(product)
                .build();
    }
}
