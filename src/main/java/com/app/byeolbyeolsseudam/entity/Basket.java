package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BASKET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket extends Period{
    @Id @GeneratedValue @NotNull
    private Long basketId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public Basket(Member member, Product product) {
        this.member = member;
        this.product = product;
    }

    public void update(Member member, Product product){
        this.member = member;
        this.product = product;
    }
}