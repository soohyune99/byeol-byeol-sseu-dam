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

    public void changeOrder(Order order){
        this.order = order;
    }

    public void changeProduct(Product product){
        this.product = product;
    }

    @Builder
    public OrderDetail(int orderDetailCount) {
        this.orderDetailCount = orderDetailCount;
    }
}
