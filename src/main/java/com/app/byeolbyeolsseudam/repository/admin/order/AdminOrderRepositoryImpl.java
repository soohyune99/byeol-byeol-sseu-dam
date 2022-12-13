package com.app.byeolbyeolsseudam.repository.admin.order;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.order.QOrderDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.QOrderDetailDTO;
import com.app.byeolbyeolsseudam.entity.order.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.order.QOrder.order;
import static com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class AdminOrderRepositoryImpl implements AdminOrderCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderDTO> showOrderList(Pageable pageable) {
        return jpaQueryFactory.select(new QOrderDTO(
                order.orderId,
                order.orderStatus,
                order.orderAddress,
                order.orderMessage,
                order.member.memberId,
                order.member.memberName,
                order.member.memberEmail,
                order.member.memberPhone,
                order.member.memberPoint,
                order.createdDate
        )).from(order)
                .orderBy(order.orderId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public void update(OrderDTO orderDTO) {
        jpaQueryFactory.selectFrom(order)
                .where(order.orderId.eq(orderDTO.getOrderId()))
                .fetchOne().update(orderDTO);
    }

    @Override
    public OrderDTO showOrderDetail(Long orderId) {
        OrderDTO orderDTO = jpaQueryFactory.select(new QOrderDTO(
                order.orderId,
                order.orderStatus,
                order.orderAddress,
                order.orderMessage,
                order.member.memberId,
                order.member.memberName,
                order.member.memberEmail,
                order.member.memberPhone,
                order.member.memberPoint,
                order.createdDate
        )).from(order)
                .where(order.orderId.eq(orderId))
                .fetchOne();

        List<OrderDetailDTO> orderDetails = jpaQueryFactory.select(new QOrderDetailDTO(
                orderDetail.orderDetailId,
                orderDetail.orderDetailCount,
                orderDetail.order.orderId,
                orderDetail.product.productId,
                orderDetail.product.productCategory,
                orderDetail.product.productName,
                orderDetail.product.productPrice,
                orderDetail.product.productFileProfileName
        )).from(orderDetail)
                .where(orderDetail.order.orderId.eq(orderDTO.getOrderId()))
                .fetch();

        orderDTO.setOrderDetails(orderDetails);
        return orderDTO;
    }
}
