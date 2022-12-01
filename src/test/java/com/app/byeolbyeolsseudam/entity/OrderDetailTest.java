package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.orderdetail.OrderDetailDTO;
import com.app.byeolbyeolsseudam.entity.orderdetail.OrderDetail;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import com.app.byeolbyeolsseudam.repository.orderdetail.OrderDetailRepository;
import com.app.byeolbyeolsseudam.repository.product.ProductRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.app.byeolbyeolsseudam.entity.orderdetail.QOrderDetail.orderDetail;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class OrderDetailTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void saveTest(){
        OrderDetailDTO orderDetail1 = new OrderDetailDTO();

        orderDetail1.setOrderDetailCount(5);
        OrderDetail orderDetailEntity = orderDetail1.toEntity();
        orderDetailEntity.changeOrder(orderRepository.findById(8L).get());
        orderDetailEntity.changeProduct(productRepository.findById(5L).get());
        orderDetailRepository.save(orderDetailEntity);
    }

    @Test
    public void findTest(){
        Optional<OrderDetail> findOrderDetail = orderDetailRepository.findById(10L);

        if(findOrderDetail.isPresent()){
            Assertions.assertThat(findOrderDetail.get().getProduct().getProductName().equals("에코백"));

            log.info("orderDetailCount : " + findOrderDetail.get().getOrderDetailCount());
        }
    }

//    @Test
//    public void queryUpdateTest(){
//        jpaQueryFactory.selectFrom(orderDetail)
//                .where(orderDetail.orderDetailId.eq(9L))
//                .orderBy(orderDetail.orderDetailId.desc())
//                .limit(1)
//                .fetchOne()
//                .update(8, jpaQueryFactory.selectFrom(order).limit(1).fetchOne(),
//                        jpaQueryFactory.selectFrom(product).limit(1).fetchOne());
//    }
//

    @Test
    public void queryDeleteTest(){
        jpaQueryFactory.delete(orderDetail)
                .where(orderDetail.orderDetailId.eq(9L))
                .execute();
    }
}
