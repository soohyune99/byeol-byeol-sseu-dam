package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Member;
import com.app.byeolbyeolsseudam.entity.Order;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private Member member;
    private LocalDateTime createdDate;

    @QueryProjection
    public OrderDTO(Long orderId, OrderStatus orderStatus, Member member, LocalDateTime createdDate) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.member = member;
        this.createdDate = createdDate;
    }

    public Order toEntity(){
        return Order.builder()
                .orderStatus(OrderStatus.주문완료)
                .member(member)
                .build();
    }
}
