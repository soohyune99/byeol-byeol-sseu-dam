package com.app.byeolbyeolsseudam.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER_DETAIL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDetail extends Period {
    @Id @GeneratedValue @NotNull
    private Long orderDetailId;
    @NotNull
    private int orderDetailCount;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @Builder
    public OrderDetail(int orderDetailCount, Order order, Product product) {
        this.orderDetailCount = orderDetailCount;
        this.order = order;
        this.product = product;
    }

    public void update(int orderDetailCount, Order order, Product product){
        this.orderDetailCount = orderDetailCount;
        this.order = order;
        this.product = product;
    }
}
