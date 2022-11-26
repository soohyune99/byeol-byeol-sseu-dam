package com.app.byeolbyeolsseudam.domain;

import com.app.byeolbyeolsseudam.entity.Order;
import com.app.byeolbyeolsseudam.entity.OrderDetail;
import com.app.byeolbyeolsseudam.entity.Product;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Component
@Data
@NoArgsConstructor
public class OrderDetailDTO {
    private Long orderDetailId;
    private int orderDetailCount;
    private Order order;
    private Product product;

    @QueryProjection
    public OrderDetailDTO(Long orderDetailId, int orderDetailCount, Order order, Product product) {
        this.orderDetailId = orderDetailId;
        this.orderDetailCount = orderDetailCount;
        this.order = order;
        this.product = product;
    }

    public OrderDetail toEntity(){
        return OrderDetail.builder()
                .orderDetailCount(orderDetailCount)
                .order(order)
                .product(product)
                .build();
    }
}
