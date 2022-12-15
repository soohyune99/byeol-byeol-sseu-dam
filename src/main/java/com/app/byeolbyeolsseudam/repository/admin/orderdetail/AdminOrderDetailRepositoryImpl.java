package com.app.byeolbyeolsseudam.repository.admin.orderdetail;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.QOrderDetailDTO;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class AdminOrderDetailRepositoryImpl implements AdminOrderDetailCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<OrderDetailDTO> showOrderDetailList(Pageable pageable) {
        return jpaQueryFactory.select(new QOrderDetailDTO(
                orderDetail.orderDetailId,
                orderDetail.orderDetailCount,
                orderDetail.order.orderId,
                orderDetail.product.productId,
                orderDetail.product.productCategory,
                orderDetail.product.productName,
                orderDetail.product.productPrice,
                orderDetail.product.productFileProfileName
        )).from(orderDetail)
                .orderBy(orderDetail.orderDetailId.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<OrderDetailDTO> showDetail(Long orderId) {
        return jpaQueryFactory.select(new QOrderDetailDTO(
                orderDetail.orderDetailId,
                orderDetail.orderDetailCount,
                orderDetail.order.orderId,
                orderDetail.product.productId,
                orderDetail.product.productCategory,
                orderDetail.product.productName,
                orderDetail.product.productPrice,
                orderDetail.product.productFileProfileName
        )).from(orderDetail)
                .where(orderDetail.order.orderId.eq(orderId))
                .fetch();
    }
}
