package com.app.byeolbyeolsseudam.repository.orderdetail;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.orderdetail.QOrderDetailDTO;
import com.app.byeolbyeolsseudam.domain.product.ProductDTO;
import com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail.orderDetail;

@Repository
@RequiredArgsConstructor
public class OrderDetailCustomRepositoryImpl implements OrderDetailCustomRepository {

    public final JPAQueryFactory jpaQueryFactory;

    @Override
    public OrderDetailDTO showOrderDetail(Long productId){
        return jpaQueryFactory.select(new QOrderDetailDTO(
                orderDetail.orderDetailId, orderDetail.orderDetailCount,
                orderDetail.order.orderId, orderDetail.product.productId,
                orderDetail.product.productCategory, orderDetail.product.productName,
                orderDetail.product.productPrice, orderDetail.product.productFileProfileName))
                .from(orderDetail)
                .where(orderDetail.product.productId.eq(productId))
                .fetchOne();
    }

}
