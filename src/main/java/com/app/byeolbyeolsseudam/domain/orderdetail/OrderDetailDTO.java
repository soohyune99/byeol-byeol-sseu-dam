package com.app.byeolbyeolsseudam.domain.orderdetail;

import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private Long orderDetailId;
    private int orderDetailCount;
    private Long orderId;
    private OrderStatus orderStatus;
    private String orderMessage;
    private Long memberId;
    private String memberName;
    private String memberAddress;
    private String memberPhone;
    private int memberPoint;

    @QueryProjection
    public OrderDetailDTO(Long orderDetailId, int orderDetailCount, Long orderId, OrderStatus orderStatus, String orderMessage, Long memberId, String memberName, String memberAddress, String memberPhone, int memberPoint) {
        this.orderDetailId = orderDetailId;
        this.orderDetailCount = orderDetailCount;
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderMessage = orderMessage;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberAddress = memberAddress;
        this.memberPhone = memberPhone;
        this.memberPoint = memberPoint;
    }

    public OrderDetail toEntity(){
        return OrderDetail.builder()
                .orderDetailCount(orderDetailCount)
                .build();
    }
}
