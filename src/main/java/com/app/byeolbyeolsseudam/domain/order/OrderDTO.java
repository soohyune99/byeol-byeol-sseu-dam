package com.app.byeolbyeolsseudam.domain.order;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long orderId;
    private OrderStatus orderStatus;
    private String orderAddress;
    private String orderMessage;
    private Long memberId;
    private String memberName;
    private String memberEmail;
    private String memberPhone;
    private int memberPoint;
    private LocalDateTime createdDate;

    private List<OrderDetailDTO> orderDetails;

    @QueryProjection
    public OrderDTO(Long orderId, OrderStatus orderStatus, String orderAddress, String orderMessage, Long memberId, String memberName, String memberEmail, String memberPhone, int memberPoint, LocalDateTime createdDate) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderAddress = orderAddress;
        this.orderMessage = orderMessage;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.memberPhone = memberPhone;
        this.memberPoint = memberPoint;
        this.createdDate = createdDate;
    }

    public Order toEntity(){
        return Order.builder()
                .orderStatus(orderStatus)
                .orderAddress(orderAddress)
                .orderMessage(orderMessage)
                .build();

    }
}


