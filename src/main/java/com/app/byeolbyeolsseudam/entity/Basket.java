package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.BasketDTO;
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
    private int basketCount;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public void changeMember(Member member){
        this.member = member;
    }

    public void changeProduct(Product product){
        this.product = product;
    }

    @Builder
    public Basket(int basketCount) {
        this.basketCount = basketCount;
    }

    public void update(Member member, Product product){
        this.member = member;
        this.product = product;
    }
}