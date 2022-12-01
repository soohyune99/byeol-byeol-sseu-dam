package com.app.byeolbyeolsseudam.entity;

import com.app.byeolbyeolsseudam.domain.order.OrderDTO;
import com.app.byeolbyeolsseudam.entity.order.Order;
import com.app.byeolbyeolsseudam.repository.member.MemberRepository;
import com.app.byeolbyeolsseudam.repository.order.OrderRepository;
import com.app.byeolbyeolsseudam.type.MemberCategory;
import com.app.byeolbyeolsseudam.type.MemberLoginType;
import com.app.byeolbyeolsseudam.type.OrderStatus;
import com.app.byeolbyeolsseudam.type.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class OrderTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveTest(){
        OrderDTO order1 = new OrderDTO();
        OrderDTO order2 = new OrderDTO();

        order1.setOrderStatus(OrderStatus.배송대기중);
        order1.setOrderAddress("서울시 사당동");
        order1.setOrderMessage("부재시 문앞에 놔주세요");

        Order orderEntity = order1.toEntity();
        orderRepository.save(orderEntity);
        orderEntity.changeMember(memberRepository.findAll().get(0));


        order2.setOrderStatus(OrderStatus.배송중);
        order2.setOrderAddress("서울시 동작구");
        order2.setOrderMessage("조심히 배송해주세요.");

        Order orderEntity2 = order2.toEntity();
        orderRepository.save(orderEntity2);
        orderEntity2.changeMember(memberRepository.findAll().get(1));
    }

    @Test
    public void findTest(){
        Optional<Order> findOrder = orderRepository.findById(2L);

        if(findOrder.isPresent()){
            Assertions.assertThat(findOrder.get().getMember().getMemberName().equals("홍길동"));

            log.info("OrderStatus : " + findOrder.get().getOrderStatus());
        }
    }

//    @Test
//    public void updateTest(){
//        Optional<Order> updateOrder = orderRepository.findById(2L);
//
//        if(updateOrder.isPresent()){
//            updateOrder.get().update(OrderStatus.배송대기중);
//        }
//    }

    @Test
    public void deleteTest(){
        Optional<Order> deleteOrder = orderRepository.findById(6L);

        if(deleteOrder.isPresent()){
            orderRepository.delete(deleteOrder.get());
        }
    }
}

