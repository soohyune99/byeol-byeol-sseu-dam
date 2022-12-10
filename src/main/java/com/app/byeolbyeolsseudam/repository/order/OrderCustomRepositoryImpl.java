package com.app.byeolbyeolsseudam.repository.order;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.domain.order.QOrderDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.QOrderDetailDTO;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.order.QOrder.order;
import static com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderDTO> selectMyOrderList(Long memberId, int page){
        List<OrderDTO> myorders = jpaQueryFactory.select(new QOrderDTO(order.orderId, order.orderStatus,
                order.orderAddress, order.orderMessage, order.member.memberId, order.member.memberName,
                order.member.memberEmail, order.member.memberPhone, order.member.memberPoint,
                order.createdDate))
                .from(order)
                .where(order.member.memberId.eq(memberId))
                .orderBy(order.createdDate.desc())
                .offset(page * 3)
                .limit(3)
                .fetch();

        myorders.stream().forEach(myorder -> {
            myorder.setOrderDetails(
                    jpaQueryFactory.select(new QOrderDetailDTO(orderDetail.orderDetailId,
                            orderDetail.orderDetailCount, orderDetail.order.orderId,
                            orderDetail.product.productId, orderDetail.product.productCategory,
                            orderDetail.product.productName, orderDetail.product.productPrice,
                            orderDetail.product.productFileProfileName))
                            .from(orderDetail)
                            .where(orderDetail.order.orderId.eq(myorder.getOrderId()))
                            .fetch()
            );
        });
        return myorders;
    }

    @Override
    public List<OrderDTO> selectMyCancelList(Long memberId, int page){
        List<OrderDTO> myorders = jpaQueryFactory.select(new QOrderDTO(order.orderId, order.orderStatus,
                order.orderAddress, order.orderMessage, order.member.memberId, order.member.memberName,
                order.member.memberEmail, order.member.memberPhone, order.member.memberPoint,
                order.createdDate))
                .from(order)
                .where(order.member.memberId.eq(memberId).and(order.orderStatus.eq(OrderStatus.주문취소)))
                .orderBy(order.createdDate.desc())
                .offset(page * 3)
                .limit(3)
                .fetch();

        myorders.stream().forEach(myorder -> {
            myorder.setOrderDetails(
                    jpaQueryFactory.select(new QOrderDetailDTO(orderDetail.orderDetailId,
                            orderDetail.orderDetailCount, orderDetail.order.orderId,
                            orderDetail.product.productId, orderDetail.product.productCategory,
                            orderDetail.product.productName, orderDetail.product.productPrice,
                            orderDetail.product.productFileProfileName))
                            .from(orderDetail)
                            .where(orderDetail.order.orderId.eq(myorder.getOrderId()))
                            .fetch()
            );
        });
        return myorders;
    }

    @Override
    public OrderDTO selectMyOrder (Long orderId){
        OrderDTO myorder = jpaQueryFactory.select(new QOrderDTO(order.orderId, order.orderStatus,
                order.orderAddress, order.orderMessage, order.member.memberId, order.member.memberName,
                order.member.memberEmail, order.member.memberPhone, order.member.memberPoint,
                order.createdDate))
                .from(order)
                .where(order.orderId.eq(orderId))
                .fetchOne();

        myorder.setOrderDetails(
                    jpaQueryFactory.select(new QOrderDetailDTO(orderDetail.orderDetailId,
                            orderDetail.orderDetailCount, orderDetail.order.orderId,
                            orderDetail.product.productId, orderDetail.product.productCategory,
                            orderDetail.product.productName, orderDetail.product.productPrice,
                            orderDetail.product.productFileProfileName))
                            .from(orderDetail)
                            .where(orderDetail.order.orderId.eq(myorder.getOrderId()))
                            .fetch()
        );
        return myorder;
    }
}
