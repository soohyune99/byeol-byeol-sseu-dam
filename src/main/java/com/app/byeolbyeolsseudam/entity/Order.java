package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_ORDER")
@Getter @Setter @ToString
public class Order extends Period {
    @Id @GeneratedValue
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
}
