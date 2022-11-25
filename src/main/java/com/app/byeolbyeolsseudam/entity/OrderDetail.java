package com.app.byeolbyeolsseudam.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER_DETAIL")
@Getter @Setter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail extends Period {
    @Id @GeneratedValue
    private Long orderDetailId;
    private int orderDetailCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
