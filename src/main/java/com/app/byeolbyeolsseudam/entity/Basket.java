package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_BASKET")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Basket extends Period{
    @Id @GeneratedValue
    private Long basketId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
