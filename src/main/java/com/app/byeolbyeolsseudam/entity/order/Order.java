package com.app.byeolbyeolsseudam.entity.order;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.entity.Period;
import com.app.byeolbyeolsseudam.entity.member.Member;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

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

    public void updateStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
