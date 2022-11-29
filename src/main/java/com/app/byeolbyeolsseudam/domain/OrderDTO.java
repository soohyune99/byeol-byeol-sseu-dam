package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Order;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private String orderAddress;
    private String orderMessage;
    private Long memberId;

    @QueryProjection
    public OrderDTO(Long orderId, OrderStatus orderStatus, String orderAddress, String orderMessage, Long memberId) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.orderMessage = orderMessage;
        this.memberId = memberId;
    }

    public Order toEntity(Member member){
        return Order.builder()
                .orderStatus(orderStatus)
                .orderAddress(orderAddress)
                .orderMessage(orderMessage)
                .member(member)
                .build();

    }
}


