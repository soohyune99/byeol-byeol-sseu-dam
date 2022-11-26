package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends Period {
    @Id @GeneratedValue @NotNull
    private Long orderId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public Order(OrderStatus orderStatus, Member member) {
        this.orderStatus = orderStatus;
        this.member = member;
    }

    public void update(OrderStatus orderStatus){
        this.orderStatus = orderStatus;
    }
}
