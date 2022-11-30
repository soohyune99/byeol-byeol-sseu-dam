package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.OrderDTO;
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
    private String orderAddress;
    private String orderMessage;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public void changeMember(Member member){
        this.member = member;
    }

    @Builder
    public Order(OrderStatus orderStatus, String orderAddress, String orderMessage) {
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.orderMessage = orderMessage;
    }

    public void update(OrderDTO orderDTO) {
        this.orderStatus = orderDTO.getOrderStatus();
    }
}
